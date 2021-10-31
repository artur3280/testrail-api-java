import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import org.apache.logging.log4j.Level;
import qa.tools.testraill.TestRail;
import qa.tools.testraill.core.Credentials;
import qa.tools.testraill.core.CustomLogger;
import qa.tools.testraill.core.artifacts.Backup;
import qa.tools.testraill.core.internal.CaseStatus;
import qa.tools.testraill.models.cases.Case;
import qa.tools.testraill.models.cases.CasesList;
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
        Integer pId = 11;
        Integer sId = 1426;

        TestRail testRail = new TestRail(new Credentials("./tr.properties"), Level.DEBUG);
        CustomLogger.log.info("Connection");
        /**
         * Get custom fields
         * @return List<CaseField>
         * @return List<ResultField>
         * */
        List<CaseField> customCaseFields = testRail.caseFields().list().execute();
        List<ResultField> customResultFields = testRail.resultFields().list().execute();

        /**
         *Get section list
         * @param projectId
         * @param suiteId
         * @return SectionsList
         * */
        SectionsList sections = testRail.sections().list(pId, sId).execute();

        /**
         * Get one section
         * @param suiteId
         * @return Section
         * */
        Section section = testRail.sections().get(sections.getSections().get(0).getId()).execute();

        /**
         * Create new section
         * @param Section
         * @return sectionId
         * */
        Section sectionBody = new Section();
        sectionBody.setName("test section");
        sectionBody.setSuiteId(sId);
        Integer sectionId = testRail.sections().add(pId, sectionBody).execute().getId();
//
        /**
         * Create new case
         * @param sectionId
         * @param Case
         * @param customCaseFields
         * @return case
         * */
        Case tCase = new Case();
        tCase.setTitle("Test case from new API");
        tCase.setSuiteId(sId);
        Case aCase = testRail.cases().add(sectionId, tCase, customCaseFields).execute();

        /**
         * Get list of runs
         * @param projectId
         * @return list of runs
         * */
        RunsList runs = testRail.runs().list(pId).queryParam("is_completed", 1).execute();

        /**
         * Create new run
         * @param Project
         * @return run
         * */
        Run newRun = new Run();
        newRun.setName("Testrun from api");
        newRun.setSuiteId(sId);
        newRun.setDescription("testDiscription ");
        newRun.setIncludeAll(true);
        Run run = testRail.runs().add(pId, newRun).execute();

        /**
         * Send results
         * @param Project
         * @return run
         * */
        ResultsList resultsList = new ResultsList();
        List<Result> resultList = new ArrayList<>();
        testRail.cases().list(pId, sId, customCaseFields).execute().getCases().forEach(c -> {
            Result result = new Result();
            result.setCaseId(c.getId());
            result.setStatusId(CaseStatus.PASSED);
            result.setComment("Some information test cases for ".concat(c.getId().toString()));
            resultList.add(result);
        });

        resultsList.setResults(resultList);
        List<Result> results = testRail.results().addForCases(run.getId(), resultsList, customResultFields)
                .execAs().as(new TypeReference<List<Result>>() {
                });


        /**
         * Save object to local artifact
         * @param Object
         * @param patch
         * @return Void
         * */
        List<Run> testRuns = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Run newRun2 = new Run();
            newRun.setName("Testrun from api");
            newRun.setSuiteId(sId);
            newRun.setDescription("testDiscription ");
            newRun.setIncludeAll(true);
            testRuns.add(newRun2);
        }
        new Backup(testRuns).saveToLocal("./artifact", "test_file");

        /**
         * Convert file to object
         * @param Object
         * @param patch
         * @return Void
         * */
        List<Run> con = new Backup().asObject("./artifact/data_test_file.json", new TypeReference<List<Run>>() {
        });

        /**
         * Delete list of cases
         * @param CasesList
         * @return CasesList
         * */

        List<Integer> ids = new ArrayList<>();
        ids.add(553878);
        ids.add(553879);
        ids.add(553880);
        CasesList l = new CasesList();
        l.setCaseIds(ids);
        testRail.cases().delete(l, sId).execute();
    }

}
