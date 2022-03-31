package anderssonderby.bestseller.automatching.models;

public class MatchingPlayersDTO {

    private String nickname;
    private String gameTitle;
    private String skillLevel;
    private String region;
    private int credits;


    public MatchingPlayersDTO(String nickname, String gameTitle, String skillLevel, String region) {
        this.nickname = nickname;
        this.gameTitle = gameTitle;
        this.skillLevel = skillLevel;
        this.region = region;
    }

    public MatchingPlayersDTO(String nickname, String gameTitle, String skillLevel, int credits, String region) {
        this.nickname = nickname;
        this.gameTitle = gameTitle;
        this.skillLevel = skillLevel;
        this.credits = credits;
        this.region = region;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
