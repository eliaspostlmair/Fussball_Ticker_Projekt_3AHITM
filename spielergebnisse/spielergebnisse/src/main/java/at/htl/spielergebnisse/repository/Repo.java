package at.htl.spielergebnisse.repository;

import at.htl.spielergebnisse.model.Match;
import at.htl.spielergebnisse.model.Team;

import java.util.*;

public class Repo {
    private static final Repo INSTANCE = new Repo();

    Set<Team> teams = new HashSet<>();
    List<Match> matches = new LinkedList<>();


    public static Repo getInstance() {
        return INSTANCE;
    }

    private Repo() {
    }

    public void addTeam(Team t) {
        teams.add(t);
    }

    public void addMatch(Match m) {matches.add(m); }

    public Team getTeam(int id) {
        for (Iterator<Team> it = teams.iterator(); it.hasNext(); ) {
            Team t= it.next();
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public List<Match> getMatches() { return matches; }

    public void clearMatches () { matches.clear(); }

 
}

