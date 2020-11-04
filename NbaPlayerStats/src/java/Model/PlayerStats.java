/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Owner
 */
public class PlayerStats {
    private String gameId;
    private String playerId;
    private String points;
    private String min;
    private String fgm;
    private String fga;
    private String fgp;
    private String ftm;
    private String fta;
    private String ftp;
    private String offReb;
    private String defReb;
    private String totReb;
    private String assists;
    private String pFouls;
    private String steals;
    private String blocks;
    private String plusMinus;

    public PlayerStats(){}
    
//    public PlayerStats(String gameId, String poStrings, String min, String fgm, String fga, String fgp){
//        this.gameId = gameId;
//        this.poStrings = poStrings;
//        this.min = min;
//        this.fgm = fgm;
//        this.fga = fga;
//        this.fgp = fgp;
//        this.ftm = ftm;
//        this.fta = fta;
//        this.ftp = ftp;
//        this.offReb = offReb;
//        this.defReb = defReb;
//        this.totReb = totReb;
//        this.assists = assists;
//        this.pFouls = pFouls;
//        this.steals = steals;
//        this.blocks = blocks;
//        this.plusMinus = plusMinus;
//    }
    
    /**
     * @return the gameId
     */
    public String getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the poStrings
     */
    public String getPoints() {
        return points;
    }

    /**
     * @param poStrings the poStrings to set
     */
    public void setPoStrings(String points) {
        this.points = points;
    }

    /**
     * @return the min
     */
    public String getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(String min) {
        this.min = min;
    }

    /**
     * @return the fgm
     */
    public String getFgm() {
        return fgm;
    }

    /**
     * @param fgm the fgm to set
     */
    public void setFgm(String fgm) {
        this.fgm = fgm;
    }

    /**
     * @return the fga
     */
    public String getFga() {
        return fga;
    }

    /**
     * @param fga the fga to set
     */
    public void setFga(String fga) {
        this.fga = fga;
    }

    /**
     * @return the fgp
     */
    public String getFgp() {
        return fgp;
    }

    /**
     * @param fgp the fgp to set
     */
    public void setFgp(String fgp) {
        this.fgp = fgp;
    }

    /**
     * @return the ftm
     */
    public String getFtm() {
        return ftm;
    }

    /**
     * @param ftm the ftm to set
     */
    public void setFtm(String ftm) {
        this.ftm = ftm;
    }

    /**
     * @return the fta
     */
    public String getFta() {
        return fta;
    }

    /**
     * @param fta the fta to set
     */
    public void setFta(String fta) {
        this.fta = fta;
    }

    /**
     * @return the ftp
     */
    public String getFtp() {
        return ftp;
    }

    /**
     * @param ftp the ftp to set
     */
    public void setFtp(String ftp) {
        this.ftp = ftp;
    }

    /**
     * @return the offReb
     */
    public String getOffReb() {
        return offReb;
    }

    /**
     * @param offReb the offReb to set
     */
    public void setOffReb(String offReb) {
        this.offReb = offReb;
    }

    /**
     * @return the defReb
     */
    public String getDefReb() {
        return defReb;
    }

    /**
     * @param defReb the defReb to set
     */
    public void setDefReb(String defReb) {
        this.defReb = defReb;
    }

    /**
     * @return the totReb
     */
    public String getTotReb() {
        return totReb;
    }

    /**
     * @param totReb the totReb to set
     */
    public void setTotReb(String totReb) {
        this.totReb = totReb;
    }

    /**
     * @return the assists
     */
    public String getAssists() {
        return assists;
    }

    /**
     * @param assists the assists to set
     */
    public void setAssists(String assists) {
        this.assists = assists;
    }

    /**
     * @return the pFouls
     */
    public String getpFouls() {
        return pFouls;
    }

    /**
     * @param pFouls the pFouls to set
     */
    public void setpFouls(String pFouls) {
        this.pFouls = pFouls;
    }

    /**
     * @return the steals
     */
    public String getSteals() {
        return steals;
    }

    /**
     * @param steals the steals to set
     */
    public void setSteals(String steals) {
        this.steals = steals;
    }

    /**
     * @return the blocks
     */
    public String getBlocks() {
        return blocks;
    }

    /**
     * @param blocks the blocks to set
     */
    public void setBlocks(String blocks) {
        this.blocks = blocks;
    }

    /**
     * @return the plusMinus
     */
    public String getPlusMinus() {
        return plusMinus;
    }

    /**
     * @param plusMinus the plusMinus to set
     */
    public void setPlusMinus(String plusMinus) {
        this.plusMinus = plusMinus;
    }

    /**
     * @return the playerId
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * @param playerId the playerId to set
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

//    /**
//     * @return the wpPlayerId
//     */
//    public String getWpPlayerId() {
//        return wpPlayerId;
//    }
//
//    /**
//     * @param wpPlayerId the wpPlayerId to set
//     */
//    public void setWpPlayerId(String wpPlayerId) {
//        this.wpPlayerId = wpPlayerId;
//    }
}
