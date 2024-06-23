package techzo.com.example.cambiazo.donations.interfaces.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetAllSchedulesByOngIdQuery;
import techzo.com.example.cambiazo.donations.domain.model.queries.GetScheduleByIdQuery;
import techzo.com.example.cambiazo.donations.domain.services.ScheduleCommandService;
import techzo.com.example.cambiazo.donations.domain.services.ScheduleQueryService;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.CreateScheduleResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.resources.ScheduleResource;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.CreateScheduleCommandFromResourceAssembler;
import techzo.com.example.cambiazo.donations.interfaces.rest.transform.ScheduleResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/schedule")
@Tag(name="Schedule", description="Schedule Management Endpoints")
public class ScheduleController {
    private final ScheduleCommandService scheduleCommandService;

    private final ScheduleQueryService scheduleQueryService;

    public ScheduleController(ScheduleCommandService scheduleCommandService, ScheduleQueryService scheduleQueryService){
        this.scheduleCommandService=scheduleCommandService;
        this.scheduleQueryService=scheduleQueryService;
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

    @Operation(summary="Get a Schedule by Id", description="Get a Schedule by Id.")
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResource>getSchedule(@PathVariable Long id){
        try {
            var getScheduleByIdQuery= new GetScheduleByIdQuery(id);
            var schedule=scheduleQueryService.handle(getScheduleByIdQuery);
            var scheduleResource= ScheduleResourceFromEntityAssembler.toResourceFromEntity(schedule.get());
            return ResponseEntity.ok(scheduleResource);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Get all Schedules by Ong Id", description="Get all Schedules by Ong Id.")
    @GetMapping("/ongs/{ongId}")
    public ResponseEntity<List<ScheduleResource>> getAllSchedulesByOngId(@PathVariable Long ongId){
        try {
            var getAllSchedulesByOngIdQuery= new GetAllSchedulesByOngIdQuery(ongId);
            var schedules=scheduleQueryService.handle(getAllSchedulesByOngIdQuery);
            var scheduleResources= schedules.stream().map(ScheduleResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
            return ResponseEntity.ok(scheduleResources);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary="Delete a Schedule by Id", description="Delete a Schedule by Id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        try {
            scheduleCommandService.handleDeleteSchedule(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
