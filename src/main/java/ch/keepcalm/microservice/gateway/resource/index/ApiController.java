package ch.keepcalm.microservice.gateway.resource.index;


import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by marcelwidmer on 04/03/18.
 */
@RestController
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class ApiController {

    @GetMapping(value =  "/")
    public ResourceSupport api() {
        ResourceSupport resourceSupport = new ResourceSupport();
        resourceSupport.add(linkTo(methodOn(ApiController.class).api()).withSelfRel());
        resourceSupport.add(linkTo(ApiController.class).slash("manage").slash("docs/manual.html").withRel("documentation"));
        resourceSupport.add(linkTo(ApiController.class).slash("/manage/routes").withRel("routes"));
        return resourceSupport;
    }


}
