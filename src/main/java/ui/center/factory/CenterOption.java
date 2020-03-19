package ui.center.factory;

public enum CenterOption {
    HOME (CenterOptionFactory.stringCodes[0]),
    LABORATORY_1 (CenterOptionFactory.stringCodes[1]),
    LABORATORY_2 (CenterOptionFactory.stringCodes[2]),
    LAMBDA_EXPRESSIONS (CenterOptionFactory.stringCodes[3]),
    MAYERYN_RECRUITMENT (CenterOptionFactory.stringCodes[4]);

    private String stringCode;

    private CenterOption(String stringCode) {
        this.stringCode = stringCode;
    }

    @Override
    public String toString() {
        return this.stringCode;
    }
}
