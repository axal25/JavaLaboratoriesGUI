package ui.center.factory;

import ui.center.options.*;

public class CenterFactory {

    public static CenterUIInterface getCenter(CenterOption centerOption) {
        switch (centerOption) {
            case HOME:
                return new HomeUI();

            case LABORATORY_1:
                return new Laboratory1();

            case LABORATORY_2:
                return new Laboratory2();

            case LAMBDA_EXPRESSIONS:
                return new LambdaExpressions();

            case MAYERYN_RECRUITMENT:
                return new MayerynRecruitment();

            default:
                throw new UnsupportedOperationException("Unsupported CenterOption: " + centerOption);
        }
    }
}
