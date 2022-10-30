package platform;

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
import java.util.UUID;

@Controller
public class CodeController {

    @Autowired
    CodeRecordService codeRecordService;

    @GetMapping(value = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getCodePageLatest(Model model) {


        model.addAttribute("codeList", codeRecordService.retrieveLatest());

        return "latest";
    }


    @GetMapping(value = "/code/{n}", produces = MediaType.TEXT_HTML_VALUE)
    public String getCodePageN(Model model, @PathVariable UUID n) {
        CodeRecord codeRecord = codeRecordService.findCodeRecordById(n);
        if(codeRecord == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"not found");
        }
        Coder coder = new Coder(codeRecord);
        model.addAttribute("codeNth", coder);
        return "codenth";
    }


    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    //@ResponseBody
    public String getNewCodePage(Model model, HttpServletResponse response) {
        String responseString = "<html>\n" +
                "<head>\n" +
                "    <title>Create</title>\n" +
                "<script>\n" +

                "function send() {\n" +
                "    let object = {\n" +
                "            \"code\": document.getElementById(\"code_snippet\").value" +
                "    };\n" +
                "    let json = JSON.stringify(object);\n" +
                "    let xhr = new XMLHttpRequest();\n" +
                "    xhr.open(\"POST\", '/api/code/new', false);\n" +
                "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "    xhr.send(json);\n" +
                "    if (xhr.status == 200) {\n" +
                "        alert(\"Success!\");\n" +
                "    }\n" +
                "}\n" +
                "</script>" +
                "</head>\n" +
                "<body>\n" +
                "    <textarea id=\"code_snippet\">\n" +
                "</textarea>\n" +
                "<input id=\"time_restriction\" type=\"text\"/>" +
                "<input id=\"views_restriction\" type=\"text\"/>" +
                "<button id=\"send_snippet\" type=\"submit\"" +
                "onclick=\"send()\">" +
                "Submit</button>" +
                "</body>\n" +
                "</html>";
        //return responseString;
        return "newcode";
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public ResponseEntity getApiCodeLastUsingResponseEntityBuilder() {

         return ResponseEntity.status(HttpStatus.OK)
                .body(codeRecordService.retrieveLatest());
    }

    @GetMapping(value = "/api/code/{n}", produces = "application/json")
    public ResponseEntity getApiCodeNUsingResponseEntityBuilder(@PathVariable UUID n) {
        CodeRecord codeRecord = codeRecordService.findCodeRecordById(n);
        if (codeRecord == null) {
            return ResponseEntity.status(404).body("not found");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Coder(codeRecord));
    }

    @PostMapping(value="api/code/new", produces="application/json")
    public ResponseEntity postApiCode(@RequestBody CodeRecord code) { //String code) {
        CodeRecord codeRecord = codeRecordService.save(code);
        UUID codeId = codeRecord.getCodeId();
        return ResponseEntity.status(HttpStatus.OK)
                .body("{\n    id : \"" + codeId.toString() + "\"\n}");
    }



}
