import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.logging.log4j.Level;
import qa.tools.testraill.TestRail;
import qa.tools.testraill.core.Credentials;
import qa.tools.testraill.core.CustomLogger;
import qa.tools.testraill.core.artifacts.Backup;
import qa.tools.testraill.core.internal.CaseStatus;
import qa.tools.testraill.models.cases.Case;
import qa.tools.testraill.models.fields.CaseField;
import qa.tools.testraill.models.fields.ResultField;
import qa.tools.testraill.models.results.Result;
import qa.tools.testraill.models.results.ResultsList;
import qa.tools.testraill.models.runs.Run;
import qa.tools.testraill.models.runs.RunsList;
import qa.tools.testraill.models.sections.Section;
import qa.tools.testraill.models.sections.SectionsList;

import java.util.ArrayList;
import java.util.List;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Integer pId=9;
        Integer sId=1351;

        TestRail testRail = new TestRail(new Credentials("./tr.properties"), Level.DEBUG);
        CustomLogger.log.info("Connection");
        List<CaseField> customCaseFields = testRail.caseFields().list().execute();
        List<ResultField> customResultFields = testRail.resultFields().list().execute();


        SectionsList sections = testRail.sections().list(pId, sId).execute();
//        System.out.println(new Gson().toJson(sections));

        Section section8 = testRail.sections().get(sections.getSections().get(0).getId()).execute();
//        System.out.println(new Gson().toJson(section8));

        Section section = new Section();
        section.setName("test section");
        section.setSuiteId(sId);

        ObjectMapper objectMapper = new ObjectMapper();
//        System.out.println(objectMapper.writeValueAsString(section));

//        System.out.println(new Gson().toJson(section));
        Integer sectionId = testRail.sections().add(pId, section).execute().getId();

        Case tCase = new Case();
        tCase.setTitle("Test case from new API");
        tCase.setSuiteId(sId);
        Case caset = testRail.cases().add(sectionId, tCase, customCaseFields).execute();
//        System.out.println(caset);

        tCase = new Case();
        tCase.setTitle("Test case from new API2");
        tCase.setSuiteId(sId);
//        caset = testRail.cases().add(sectionId, tCase, customCaseFields).execute();
//        System.out.println(caset);

        RunsList runs = testRail.runs().list(pId).queryParam("is_completed", 1).execute();
//        System.out.println(new ObjectMapper().writeValueAsString(runs));

        Run run;
//        System.out.println(new ObjectMapper().writeValueAsString(run));

        Run newRun =  new Run();
        newRun.setName("Testrun from api");
        newRun.setSuiteId(sId);
        newRun.setDescription("testDiscription ");
        newRun.setIncludeAll(true);
        run = testRail.runs().add(pId, newRun).execute();

        ResultsList resultsList = new ResultsList();
        List<Result> resultList = new ArrayList<>();
        testRail.cases().list(pId, sId, customCaseFields).execute().getCases().forEach(c->{
            Result result = new Result();
            result.setCaseId(c.getId());
            result.setStatusId(CaseStatus.PASSED);
            result.setComment("Some information test cases for ".concat(c.getId().toString()));
            resultList.add(result);
        });

        resultsList.setResults(resultList);
//        System.out.println(new ObjectMapper().writeValueAsString(resultsList));
        List<Result> t = testRail.results()
                .addForCases(run.getId(), resultsList, customResultFields)
                .execAs().as(new TypeReference<List<Result>>() {
                });
        t.forEach(r->{
//            System.out.println(r.getId());
        });

        List<Run> testRuns = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Run newRun2 =  new Run();
            newRun.setName("Testrun from api");
            newRun.setSuiteId(sId);
            newRun.setDescription("testDiscription ");
            newRun.setIncludeAll(true);
            testRuns.add(newRun2);
        }

        new Backup(testRuns).saveToLocal("./artifact", "testfile");

        List<Run> con = new Backup().asObject("./artifact/data_testfile.json", new TypeReference<List<Run>>() {});


    }

}
