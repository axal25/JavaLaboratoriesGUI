package ui.center.factory;

import ui.center.options.*;
import ui.center.options.home.HomeUI;
import ui.center.options.laboratory1.Laboratory1UI;
import ui.center.options.laboratory2.Laboratory2UI;
import ui.center.options.lambda.expressions.LambdaExpressionsUI;
import ui.center.options.mayeryn.recruitment.MayerynRecruitmentUI;

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
