package ui.center.factory;

import ui.center.options.*;

public class CenterFactory {

    public static AbstractCenterUI getCenter(CenterOption centerOption, String exercise) {
        switch (centerOption) {
            case HOME:
                return new HomeUI(exercise);

            case LABORATORY_1:
                return new Laboratory1UI(exercise);

            case LABORATORY_2:
                return new Laboratory2UI(exercise);

            case LAMBDA_EXPRESSIONS:
                return new LambdaExpressionsUI(exercise);

            case MAYERYN_RECRUITMENT:
                return new MayerynRecruitmentUI(exercise);

            default:
                throw new UnsupportedOperationException("Unsupported CenterOption: " + centerOption);
        }
    }
}
