import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import qa.tools.testraill.TestRail;
import qa.tools.testraill.core.Credentials;
import qa.tools.testraill.models.cases.Case;
import qa.tools.testraill.models.fields.CaseField;
import qa.tools.testraill.models.fields.ResultField;
import qa.tools.testraill.models.sections.Section;
import qa.tools.testraill.models.sections.SectionsList;

import java.util.List;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Credentials credentials = new Credentials();
        credentials.setUsername("ognezdyonova@healthrecoverysolutions.com");
        credentials.setPassword("Indus@2313");
        credentials.setBaseUrl("https://hrs.testrail.net/");
        credentials.setAppName("Test.project.for.refactoring[do_not_using]");

        Integer pId=9;
        Integer sId=1351;

        TestRail testRail = new TestRail(credentials);
        List<CaseField> customCaseFields = testRail.caseFields().list().execute();
        List<ResultField> customResultFields = testRail.resultFields().list().execute();


        SectionsList sections = testRail.sections().list(pId, sId).execute();
        System.out.println(new Gson().toJson(sections));

        Section section8 = testRail.sections().get(sections.getSections().get(0).getId()).execute();
        System.out.println(new Gson().toJson(section8));

        Section section = new Section();
        section.setName("test section");
        section.setSuiteId(sId);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(section));

        System.out.println(new Gson().toJson(section));
        Integer sectionId = testRail.sections().add(pId, section).execute().getId();

        Case tCase = new Case();
        tCase.setTitle("Test case from new API");
        tCase.setSuiteId(sId);
        Case caset = testRail.cases().add(sectionId, tCase, customCaseFields).execute();
        System.out.println(caset);
    }
}
