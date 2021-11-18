package com.example.restwt.controllers;

import com.example.restwt.models.*;
import com.example.restwt.services.MainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MainController {

    final
    MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping(value = "/show-material", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> showMaterial(@RequestParam String model, @RequestParam String brokenPart) throws IOException {
        return new ResponseEntity<>("{\"materialName\" : \""+mainService.showMaterial(model,brokenPart)+"\"}",HttpStatus.OK);
    }
    @RequestMapping(value = "/order-choice", method = RequestMethod.GET)
    public ResponseEntity<Material> orderingChoice(@RequestParam String model, @RequestParam String brokenPart) throws IOException {
        return new ResponseEntity<>(mainService.orderingChoice(model,brokenPart), HttpStatus.OK);
    }
    @RequestMapping(value="/find-friend",method = RequestMethod.GET)
    public ResponseEntity<Friend> findFriend(@RequestParam String model,@RequestParam String brokenPart) throws IOException {
        return new ResponseEntity<>(mainService.findFriend(model,brokenPart),HttpStatus.OK);
    }
    @RequestMapping(value="/choose-friend",method = RequestMethod.GET)
    public ResponseEntity<Friend> chooseFriend(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML,
                                               @RequestParam String model,@RequestParam String brokenPart) throws IOException {
        return new ResponseEntity<>(mainService.findFriend(model,brokenPart),HttpStatus.OK);
    }
    @RequestMapping(value="/choose-friend-tool",method = RequestMethod.GET)
    public ResponseEntity<Friend> chooseFriendTool(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML,
                                               @RequestParam String toolName) throws IOException {
        return new ResponseEntity<>(mainService.findFriend(toolName),HttpStatus.OK);
    }
    @RequestMapping(value="/buy-it",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> buyIt(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML) throws IOException {
        return new ResponseEntity<>("{\"message\" : \""+mainService.buyIt(Role,Org,HTML)+"\"}",HttpStatus.OK);
    }
    @RequestMapping(value="/find-friend-tool",method = RequestMethod.GET)
    public ResponseEntity<Friend> findFriendTool(@RequestParam String toolName) throws IOException {
        return new ResponseEntity<>(mainService.findFriend(toolName),HttpStatus.OK);
    }
    @RequestMapping(value="/friends-borrowed",method=RequestMethod.POST)
    public ResponseEntity<Boolean> addInFriendsBorrowed(@RequestParam String friendsName,@RequestParam String toolOrMaterial) throws IOException {
        return new ResponseEntity<>(mainService.addInFriendsBorrowed(friendsName,toolOrMaterial),HttpStatus.OK);
    }
    @RequestMapping(value="/add-order",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrder(@RequestParam String toolOrMaterialName,@RequestParam Boolean buyOrBorrow) throws IOException {
        return new ResponseEntity<>("{\"orderID\" : \""+mainService.addOrder(toolOrMaterialName,buyOrBorrow)+"\"}",HttpStatus.OK);
    }
    @RequestMapping(value = "/show-tool", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> showTool(@RequestParam String model, @RequestParam String brokenPart) throws IOException {
        return new ResponseEntity<>("{\"toolName\" : \""+mainService.showTool(model,brokenPart)+"\"}",HttpStatus.OK);
    }
    @RequestMapping(value = "/randomize-borrow-time", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> showTool(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML) throws IOException {
        return new ResponseEntity<>("{\"time\" : \""+mainService.time(Role,Org,HTML)+"\"}",HttpStatus.OK);
    }
    @RequestMapping(value = "/order-choice-tool", method = RequestMethod.GET)
    public ResponseEntity<Tool> orderingChoiceTool(@RequestParam String model, @RequestParam String brokenPart) throws IOException {
        return new ResponseEntity<>(mainService.orderingChoiceTool(model,brokenPart), HttpStatus.OK);
    }
    @RequestMapping(value="/receive-instructions",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> receiveInstructions(@RequestParam String model,@RequestParam String brokenPart) throws IOException {
        return new ResponseEntity<>("{\"instructions\" : \""+mainService.showInstructions(model,brokenPart)+"\"}",HttpStatus.OK);
    }
    @RequestMapping(value="/randomize-time-to-fix", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> randomizeTimeToFix(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML)  {
        return new ResponseEntity<>("{\"timeToFix\" : \""+mainService.timeToFix(Role,Org,HTML)+"\"}",HttpStatus.OK);
    }
    @RequestMapping(value="/randomize-repair-worked", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> randomizeRepairWorked(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML)  {
        return new ResponseEntity<>("{\"repairWorked\" : \""+mainService.repairWorked(Role,Org,HTML)+"\"}",HttpStatus.OK);
    }
    @RequestMapping(value="/prepare-repairment-data", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wrapper> prepareData(@RequestParam Boolean repairWorked)
    {
        return new ResponseEntity<>(mainService.prepareData(repairWorked),HttpStatus.OK);
    }
    @RequestMapping(value="/create-review", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wrapper> createReview(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML,@RequestParam Boolean repairWorked)
    {
        return new ResponseEntity<>(mainService.createReview(Role,Org,HTML,repairWorked),HttpStatus.OK);
    }
    @RequestMapping(value="/create-review-and-video-choice", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wrapper> createVideoAndChoice(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML,
    @RequestParam String reason,@RequestParam String review)
    {
        return new ResponseEntity<>(mainService.createSendVideoAndReview(Role,Org,HTML,reason,review),HttpStatus.OK);
    }
    @RequestMapping(value="/create-video-name", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wrapper> createVideoName(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML)
    {
        return new ResponseEntity<>(mainService.createVideoName(Role,Org,HTML),HttpStatus.OK);
    }
    @RequestMapping(value="/create-reason-not-worked", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wrapper> createReasonNotWorked(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML)
    {
        return new ResponseEntity<>(mainService.createReasonNotWorked(Role,Org,HTML),HttpStatus.OK);
    }
    @RequestMapping(value="/create-repair-man-name", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wrapper> createRepairManName(@RequestParam String Role,@RequestParam String Org,@RequestParam String HTML)
    {
        return new ResponseEntity<>(mainService.createRepairManName(Role,Org,HTML),HttpStatus.OK);
    }
    @RequestMapping(value="/randomize-model-and-part", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WrapperModelBrokenPart> createModelAndBrokenPart(@RequestParam String Role, @RequestParam String Org, @RequestParam String HTML)
    {
        return new ResponseEntity<>(mainService.createModelAndPart(Role,Org,HTML),HttpStatus.OK);
    }
    @RequestMapping(value="/receive",method=RequestMethod.POST)
    public ResponseEntity<Boolean> receiveFromCPEE(@RequestHeader MultiValueMap<String, String> headers, @RequestParam Map<String, String> body)
    {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("CPEE-CALLBACK",
                "true");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(mainService.receive(headers,body));
    }
    @RequestMapping(value="/worklist",method=RequestMethod.GET)
    public ResponseEntity<String> worklist(@RequestParam String name) throws IOException {
        return ResponseEntity.ok().body(mainService.worklist(name));
    }
    @RequestMapping(value="/final",method=RequestMethod.GET)
    public ResponseEntity<Boolean> finalFile(@RequestParam Map<String, String> params) throws IOException {
        return ResponseEntity.ok().body(mainService.finalFile(params));
    }
    @RequestMapping(value="/work",method=RequestMethod.GET)
    public ResponseEntity<String> finalFile(@RequestParam String callbackURL,@RequestParam String htmlForm) throws IOException {
        return ResponseEntity.ok().body(mainService.work(callbackURL,htmlForm));
    }
    @RequestMapping(value="/give-back",method=RequestMethod.GET)
    public ResponseEntity<Boolean> giveBack(@RequestParam Map<String, String> params) throws IOException {
        return ResponseEntity.ok().body(mainService.giveBack(params));
    }

}
