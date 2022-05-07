public class Option {
     int optionId;
     String optionValue;

    public Option() {
    }

    public Option(int optionId, String optionValue) {
        this.optionId = optionId;
        this.optionValue = optionValue;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    @Override
    public String toString() {
        return "Option{" +
                "optionId=" + optionId +
                ", optionValue='" + optionValue + '\'' +
                '}';
    }
}
