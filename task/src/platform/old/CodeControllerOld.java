package platform.old;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import platform.CodeRecordStore;
import platform.businesslayer.CodeRecord;

import javax.servlet.http.HttpServletResponse;

/*@Controller
public class CodeControllerOld {

    private CodeRecordStore codeStore = new CodeRecordStore();

    @GetMapping(value = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String getCodePageLatest(Model model) {
        model.addAttribute("codeList", codeStore.retrieveLatest());

        return "latest";
    }


    @GetMapping(value = "/code/{n}", produces = MediaType.TEXT_HTML_VALUE)
    public String getCodePageN(Model model, @PathVariable Integer n) {

        model.addAttribute("codeNth", codeStore.retrieveNth(n));
        return "codenth";
    }


    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getNewCodePage(HttpServletResponse response) {
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
                "<button id=\"send_snippet\" type=\"submit\"" +
                "onclick=\"send()\">" +
                "Submit</button>" +
                "</body>\n" +
                "</html>";
        return responseString;
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public ResponseEntity getApiCodeLastUsingResponseEntityBuilder() {

         return ResponseEntity.status(HttpStatus.OK)
                .body(codeStore.retrieveLatest());
    }

    @GetMapping(value = "/api/code/{n}", produces = "application/json")
    public ResponseEntity getApiCodeNUsingResponseEntityBuilder(@PathVariable Integer n) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(codeStore.retrieveNth(n));
    }

    @PostMapping(value="api/code/new", produces="application/json")
    public ResponseEntity postApiCode(@RequestBody CodeRecord code) { //String code) {
        Integer n = this.codeStore.addCodeRecord(code);
        return ResponseEntity.status(HttpStatus.OK)
                .body("{\n    id : \"" + n.toString() + "\"\n}");
    }



}*/
