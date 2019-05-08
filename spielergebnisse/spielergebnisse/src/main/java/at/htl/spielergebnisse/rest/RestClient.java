package at.htl.spielergebnisse.rest;

import at.htl.spielergebnisse.model.Match;
import at.htl.spielergebnisse.model.Team;
import at.htl.spielergebnisse.repository.Repo;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

/**
 * https://jersey.github.io/documentation/latest/client.html#d0e4716
 * https://www.baeldung.com/jersey-jax-rs-client
 * https://github.com/OpenLigaDB/OpenLigaDB-Samples
 * https://o7planning.org/en/10437/java-json-processing-api-tutorial
 */
public class RestClient {

    public static final String URI = "https://www.openligadb.de/api/";
    private Repo repo = Repo.getInstance();

    Client client = ClientBuilder.newClient();

    /**
     * https://www.openligadb.de/api/getavailableteams/bl1/2018
     *
     * @return
     */
    public int importTeams(String leagueShortcut, int leagueSeason) {

        Response resp = client
                .target(URI)
                .path("getavailableteams/" + leagueShortcut + "/" + leagueSeason)
                .request(MediaType.APPLICATION_JSON)
                .get();

        String teams = resp.readEntity(String.class);

        // javax.json
        JsonArray teamsJson = Json.createReader(new StringReader(teams)).readArray();

        for (JsonValue val : teamsJson) {
            if (val.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject o = val.asJsonObject();
                int id = o.getInt("TeamId");
                String name = o.getString("TeamName");
                String shortName = o.getString("ShortName");
                String iconUrl = o.getString("TeamIconUrl");
                repo.addTeam(new Team(id, name, shortName, iconUrl));
            }
        }
        return resp.getStatus();

    }

    public void importMatches(String leagueShortcut, int leagueSeason, int groupOrderId){

        repo.clearMatches();

        Response resp = client
                .target(URI)
                .path("getmatchdata/" + leagueShortcut + "/" + leagueSeason + "/" + groupOrderId)
                .request(MediaType.APPLICATION_JSON)
                .get();

        String matches = resp.readEntity(String.class);

        JsonArray matchesJson = Json.createReader(new StringReader(matches)).readArray();

        for (JsonValue val : matchesJson) {
            if (val.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject o = val.asJsonObject();
                int matchId = o.getInt("MatchID");
                LocalDateTime dateTime = LocalDateTime.parse(o.getString("MatchDateTime"));
                Team team1 = repo.getTeam(o.getJsonObject("Team1").getInt("TeamId"));
                Team team2 = repo.getTeam(o.getJsonObject("Team2").getInt("TeamId"));
                int team1Score = 0;
                int team2Score = 0;
                if(!o.getJsonArray("MatchResults").isEmpty()){
                    team1Score = o.getJsonArray("MatchResults").get(1).asJsonObject().getInt("PointsTeam1");
                    team2Score = o.getJsonArray("MatchResults").get(1).asJsonObject().getInt("PointsTeam2");
                }
                Match match = new Match(matchId, dateTime, team1, team2, team1Score, team2Score);
                repo.addMatch(match);
            }
        }
    }
}

