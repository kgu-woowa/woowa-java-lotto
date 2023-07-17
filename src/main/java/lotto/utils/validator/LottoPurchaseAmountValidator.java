package lotto.utils.validator;

import static lotto.utils.ExceptionConstants.InputException.PURCHASE_AMOUNT_MUST_BE_THOUSAND_UNIT;

public class LottoPurchaseAmountValidator extends Validator {
    private static final int PURCHASE_UNIT = 1000;

    @Override
    public void validate(final String userInput) {
        validateInputHasSpace(userInput);
        validateInputIsNumeric(userInput);
        validateUnitOfAmountIsThousand(userInput);
    }

    private void validateUnitOfAmountIsThousand(String userInput) {
        if (Integer.parseInt(userInput) % PURCHASE_UNIT != 0) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_MUST_BE_THOUSAND_UNIT.message);
        }
    }
}