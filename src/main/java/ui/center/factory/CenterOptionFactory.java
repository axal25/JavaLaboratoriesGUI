package ui.center.factory;

public class CenterOptionFactory {
    public static final String[] stringCodes = {
            "Home",
            "Laboratory #1",
            "Laboratory #2",
            "Lambda expressions",
            "\"Mayeryn\" recruitment",
    };

    public static CenterOption getCenterOption(String stringCode) {
        if(stringCode.compareTo(CenterOptionFactory.stringCodes[0]) == 0) return CenterOption.HOME;
        else if(stringCode.compareTo(CenterOptionFactory.stringCodes[1]) == 0) return CenterOption.LABORATORY_1;
        else if(stringCode.compareTo(CenterOptionFactory.stringCodes[2]) == 0) return CenterOption.LABORATORY_2;
        else if(stringCode.compareTo(CenterOptionFactory.stringCodes[3]) == 0) return CenterOption.LAMBDA_EXPRESSIONS;
        else if(stringCode.compareTo(CenterOptionFactory.stringCodes[4]) == 0) return CenterOption.MAYERYN_RECRUITMENT;
        else throw new UnsupportedOperationException("Unsupported String: \"" + stringCode + "\"");
    }
}
