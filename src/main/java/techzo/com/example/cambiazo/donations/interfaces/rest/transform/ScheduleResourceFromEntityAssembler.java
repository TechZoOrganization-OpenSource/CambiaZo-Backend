package techzo.com.example.cambiazo.donations.interfaces.rest.transform;

import techzo.com.example.cambiazo.donations.domain.model.aggregates.Schedule;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.ScheduleResource;

public class ScheduleResourceFromEntityAssembler {

    public static ScheduleResource toResourceFromEntity(Schedule entity) {
        return new ScheduleResource(entity.getId(), entity.getText(), entity.getOngId());
    }
}
