/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htl.spielergebnisse.model;

import java.time.LocalDateTime;

/**
 *
 * @author elias
 */
public class Match {

    private int matchId;
    private LocalDateTime matchDatetime;
    private Team team1;
    private Team team2;
    private int team1Score;
    private int team2Score;

    //region constructors
    public Match() {
    }

    public Match(int matchId, LocalDateTime matchDatetime, Team team1, Team team2, int team1Score, int team2Score) {
        this.matchId = matchId;
        this.matchDatetime = matchDatetime;
        this.team1 = team1;
        this.team2 = team2;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
    }
    //endregion

    //region getter and setter
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public LocalDateTime getMatchDatetime() {
        return matchDatetime;
    }

    public void setMatchDatetime(LocalDateTime matchDatetime) {
        this.matchDatetime = matchDatetime;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    //endregion


    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", matchDatetime=" + matchDatetime +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }
}
