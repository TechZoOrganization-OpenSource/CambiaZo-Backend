package techzo.com.example.cambiazo.donations.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techzo.com.example.cambiazo.donations.domain.services.ScheduleCommandService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateScheduleResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.ScheduleResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateScheduleCommandFromResourceAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.ScheduleResourceFromEntityAssembler;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/schedule")
@Tag(name="Schedule", description="Schedule Management Endpoints")
public class ScheduleController {
    private final ScheduleCommandService scheduleCommandService;

    public ScheduleController(ScheduleCommandService scheduleCommandService){
        this.scheduleCommandService=scheduleCommandService;
    }

    @Operation(summary="Create a new Schedule", description="Create a new Schedule with the input data.")
    @PostMapping
    public ResponseEntity<ScheduleResource>createSchedule(@RequestBody CreateScheduleResource resource){
        try {
            var createScheduleCommand= CreateScheduleCommandFromResourceAssembler.toCommandFromResource(resource);
            var schedule=scheduleCommandService.handle(createScheduleCommand);
            var scheduleResource= ScheduleResourceFromEntityAssembler.toResourceFromEntity(schedule.get());
            return ResponseEntity.status(CREATED).body(scheduleResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
