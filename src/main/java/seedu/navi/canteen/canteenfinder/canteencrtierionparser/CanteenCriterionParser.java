package seedu.navi.canteen.canteenfinder.canteencrtierionparser;

import seedu.navi.canteen.usershortcuts.CanteenCriteriaShortcuts;
import seedu.navi.exceptions.DuplicateCanteenCriterion;
import seedu.navi.exceptions.EmptyCanteenCriteria;
import seedu.navi.exceptions.HCAndMOCrtieriaError;
import seedu.navi.exceptions.InvalidCanteenCriteria;
import seedu.navi.exceptions.NILWithOtherCriteria;

import java.util.HashSet;
import java.util.Set;

public class CanteenCriterionParser {

    private static void verifyCanteenCriteria(String[] canteenCriteria)
            throws NILWithOtherCriteria, DuplicateCanteenCriterion, InvalidCanteenCriteria {
        Set<String> uniqueCriteria = new HashSet<>();
        String[] validCanteenCriteria = new String[canteenCriteria.length];

        boolean isNILPresent = false;
        boolean isHalalCertifiedPresent = false;
        boolean isMuslimOwnedPresent = false;

        assert canteenCriteria.length > 0 : "canteenCriteria array should not be empty.";

        for (int i = 0; i < canteenCriteria.length; i++) {
            String lowerCaseCriterion = canteenCriteria[i].toLowerCase().trim();
            String validCanteenCriterion = CanteenCriteriaShortcuts.CANTEEN_CRITERIA_MAP.get(lowerCaseCriterion);
            if (validCanteenCriterion == null) {
                throw new InvalidCanteenCriteria();
            }
            // Check for duplicates
            if (!uniqueCriteria.add(validCanteenCriterion)) {
                throw new DuplicateCanteenCriterion();
            }
            if ("nil".equals(validCanteenCriterion)) {
                isNILPresent = true;
            }
            if ("halal certified".equals(validCanteenCriterion)) {
                isHalalCertifiedPresent = true;
            }
            if ("muslim owned".equals(validCanteenCriterion)) {
                isMuslimOwnedPresent = true;
            }
            validCanteenCriteria[i] = validCanteenCriterion;
        }
        if (isHalalCertifiedPresent && isMuslimOwnedPresent) {
            throw new HCAndMOCrtieriaError();
        }
        if (isNILPresent && uniqueCriteria.size() > 1) {
            throw new NILWithOtherCriteria();
        }
        System.arraycopy(validCanteenCriteria, 0, canteenCriteria, 0, validCanteenCriteria.length);
    }

    public static String[] handleCanteenCriterion(String canteenCriterion) throws EmptyCanteenCriteria,
            InvalidCanteenCriteria, NILWithOtherCriteria, DuplicateCanteenCriterion {
        if (canteenCriterion.isEmpty()) {
            throw new EmptyCanteenCriteria();
        }

        String[] canteenCriteria = canteenCriterion.split(",");
        if (canteenCriteria.length == 0) {
            throw new InvalidCanteenCriteria();
        }

        verifyCanteenCriteria(canteenCriteria);

        if (canteenCriteria[0].trim().equalsIgnoreCase("nil")) {
            canteenCriteria = null;
        }
        return canteenCriteria;
    }
}
