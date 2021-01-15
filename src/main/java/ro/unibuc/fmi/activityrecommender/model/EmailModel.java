package ro.unibuc.fmi.activityrecommender.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class EmailModel {

    private final String receivers;

    private final String subject;

    private final String message;
}
