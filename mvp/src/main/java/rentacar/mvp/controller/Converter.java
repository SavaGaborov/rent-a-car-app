package rentacar.mvp.controller;

import rentacar.mvp.controller.borrower.response.GetBorrowerResponse;
import rentacar.mvp.controller.staff.response.GetStaffResponse;
import rentacar.mvp.model.User;

/**
 * Created by savagaborov on 26.1.2020
 */
public class Converter {

    public static GetStaffResponse toGetStaffResponse(User user) {
        return GetStaffResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone_number(user.getPhoneNumber())
                .deleted(user.getDeleted())
                .build();
    }

    public static GetBorrowerResponse toGetBorrowerResponse(User user) {
        return GetBorrowerResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone_number(user.getPhoneNumber())
                .deleted(user.getDeleted())
                .build();
    }
}
