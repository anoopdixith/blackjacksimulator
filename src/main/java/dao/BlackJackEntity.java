package dao;

/**
 * Created by adixith.
 */
public class BlackJackEntity {
    private int numberOfDecks;
    private boolean soft17;
    private boolean doubleAllowed;
    private boolean splitAllowed;
    private boolean pushBetAllowed;
    private boolean bustBetAllowed;
    private boolean match678Allowed;
    private boolean match777Allowed;
    private boolean match19Allowed;
    private boolean match20Allowed;
    private boolean matchSuited21Allowed;
    private boolean matchUnsuited21Allowed;

    public BlackJackEntity() {
        this.numberOfDecks = 6;
        this.doubleAllowed = true;
        this.splitAllowed = true;
        this.soft17 = true;
    }

    public BlackJackEntity(int numberOfDecks, boolean soft17,
                           boolean doubleAllowed, boolean splitAllowed,
                           boolean pushBetAllowed, boolean bustBetAllowed,
                           boolean match678Allowed, boolean match777Allowed,
                           boolean match19Allowed, boolean match20Allowed,
                           boolean matchSuited21Allowed, boolean matchUnsuited21Allowed) {
        this.numberOfDecks = numberOfDecks;
        this.soft17 = soft17;
        this.doubleAllowed = doubleAllowed;
        this.splitAllowed = splitAllowed;
        this.pushBetAllowed = pushBetAllowed;
        this.bustBetAllowed = bustBetAllowed;
        this.match678Allowed = match678Allowed;
        this.match777Allowed = match777Allowed;
        this.match19Allowed = match19Allowed;
        this.match20Allowed = match20Allowed;
        this.matchSuited21Allowed = matchSuited21Allowed;
        this.matchUnsuited21Allowed = matchUnsuited21Allowed;
    }

    public BlackJackEntity(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public void setNumberOfDecks(int deck) {
        this.numberOfDecks = deck;
    }

    public boolean isSoft17() {
        return soft17;
    }

    public void setSoft17(boolean soft17) {
        this.soft17 = soft17;
    }

    public boolean isDoubleAllowed() {
        return doubleAllowed;
    }

    public void setDoubleAllowed(boolean doubleAllowed) {
        this.doubleAllowed = doubleAllowed;
    }

    public boolean isSplitAllowed() {
        return splitAllowed;
    }

    public void setSplitAllowed(boolean splitAllowed) {
        this.splitAllowed = splitAllowed;
    }

    public boolean isPushAllowed() {
        return pushBetAllowed;
    }

    public void setPushAllowed(boolean pushAllowed) {
        this.pushBetAllowed = pushAllowed;
    }

    public boolean isBustBetAllowed() {
        return bustBetAllowed;
    }

    public void setBustBetAllowed(boolean bustBetAllowed) {
        this.bustBetAllowed = bustBetAllowed;
    }

    public boolean isMatch678Allowed() {
        return match678Allowed;
    }

    public void setMatch678Allowed(boolean match678Allowed) {
        this.match678Allowed = match678Allowed;
    }

    public boolean isMatch777Allowed() {
        return match777Allowed;
    }

    public void setMatch777Allowed(boolean match777Allowed) {
        this.match777Allowed = match777Allowed;
    }

    public boolean isMatch19Allowed() {
        return match19Allowed;
    }

    public void setMatch19Allowed(boolean match19Allowed) {
        this.match19Allowed = match19Allowed;
    }

    public boolean isMatch20Allowed() {
        return match20Allowed;
    }

    public void setMatch20Allowed(boolean match20Allowed) {
        this.match20Allowed = match20Allowed;
    }

    public boolean isMatchSuited21Allowed() {
        return matchSuited21Allowed;
    }

    public void setMatchSuited21Allowed(boolean matchSuited21Allowed) {
        this.matchSuited21Allowed = matchSuited21Allowed;
    }

    public boolean isMatchUnsuited21Allowed() {
        return matchUnsuited21Allowed;
    }

    public void setMatchUnsuited21Allowed(boolean matchUnsuited21Allowed) {
        this.matchUnsuited21Allowed = matchUnsuited21Allowed;
    }
}
