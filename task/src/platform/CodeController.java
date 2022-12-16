package platform;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.businesslayer.CodeRecord;
import platform.businesslayer.CodeRecordService;
import platform.businesslayer.Coder;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class CodeController {

    @Autowired
    CodeRecordService codeRecordService;
    private static final Logger logger = LogManager.getLogger(CodeController.class);

    @GetMapping(value = "/logger-test", produces = MediaType.TEXT_HTML_VALUE)
    public String index(Model model) {
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
        logger.warn("Warn log message");
        logger.fatal("Fatal log message");
        logger.trace("Trace log message");

        String responseString = "Howdy! Check out the Logs to see the output...";
        model.addAttribute("responseString", responseString);
        return "loggertest";
    }

    @GetMapping(value = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getCodePageLatest(Model model) {
        List<Coder> coderList = codeRecordService.retrieveLatest();
        model.addAttribute("codeList", coderList);
        logger.debug( coderList.stream().map(coder1 -> "\n/code/latest " + coder1.getCode() + " " + coder1.getDate()).toList());
        return "latest";
    }

    @GetMapping(value = "/code/{n}", produces = MediaType.TEXT_HTML_VALUE)
    public String getCodePageN(Model model, @PathVariable UUID n) {
        CodeRecord codeRecord = codeRecordService.findCodeRecordById(n);
        if(codeRecord == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"not found");
        }
        //Coder coder = new Coder(codeRecord);
        model.addAttribute("codeRecordNth", codeRecord);
        logger.debug("\n/code/{n} " + codeRecord.getCode() + " T: " + codeRecord.getTime() + " V: " + codeRecord.getViews());
        return "codenth";
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewCodePage(Model model, HttpServletResponse response) {
        logger.debug("\n/code/new Get");
        return "newcode";
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public ResponseEntity getApiCodeLastUsingResponseEntityBuilder() {
        //slowDown();
        List<Coder> coder = codeRecordService.retrieveLatest();
        logger.debug( coder.stream().map(coder1 -> "\n/api/code/latest " + coder1.getCode() + " " + coder1.getDate()).toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(coder);
    }

    @GetMapping(value = "/api/code/{n}", produces = "application/json")
    public ResponseEntity getApiCodeNUsingResponseEntityBuilder(@PathVariable UUID n) {
        CodeRecord codeRecord = codeRecordService.findCodeRecordById(n);
        if (codeRecord == null) {
            logger.debug("\n/api/code/{n} not found");
            return ResponseEntity.status(404).body("not found");
        }
        Coder coder = new Coder(codeRecord);
        logger.debug("\n/api/code/{n} " + coder.getCode() + " T: " + coder.getTime() + " V: " + coder.getViews());
        return ResponseEntity.status(HttpStatus.OK)
                .body(coder);
    }

    @PostMapping(value="api/code/new", produces="application/json")
    public ResponseEntity postApiCode(@RequestBody CodeRecord code) {
        if (code.getViews() > 0) {
            code.setViews(code.getViews()); // in order to differentiate from 0 or no restriction during display
        }
        CodeRecord codeRecord = codeRecordService.save(code);
        UUID codeId = codeRecord.getCodeId();
        logger.debug("\napi/code/new " + codeRecord.getCode());
        return ResponseEntity.status(HttpStatus.OK)
                .body("{\n    id : \"" + codeId.toString() + "\"\n}");
    }



}
