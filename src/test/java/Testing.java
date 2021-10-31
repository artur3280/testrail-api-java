import org.apache.logging.log4j.Level;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import qa.tools.testraill.TestRail;
import qa.tools.testraill.core.Credentials;
import qa.tools.testraill.core.CustomLogger;
import qa.tools.testraill.models.cases.Case;
import qa.tools.testraill.models.cases.CasesList;
import qa.tools.testraill.models.fields.CaseField;
import qa.tools.testraill.models.fields.ResultField;
import qa.tools.testraill.models.sections.Section;
import qa.tools.testraill.models.sections.SectionsList;

import java.util.ArrayList;
import java.util.List;

public class Testing extends Assert {
    private TestRail testRail;
    Integer pId = 11;
    Integer sId = 1426;
    private Section section;
    private Section newSection;
    private List<CaseField> customCaseFields;
    private Case aCase;

    @BeforeClass
    public void auth() {


        testRail = new TestRail(new Credentials("./tr.properties"), Level.DEBUG);
        CustomLogger.log.info("Connection");
    }

    @Test
    public void testCustomCaseFields() {
        /**
         * Get custom fields
         * @return List<CaseField>
         * */
        customCaseFields = testRail.caseFields().list().execute();
        assertNotNull(customCaseFields);
    }

    @Test
    public void testCustomResultsFields() {
        /**
         * Get custom fields
         * @return List<ResultField>
         * */
        List<ResultField> customResultFields = testRail.resultFields().list().execute();
        assertNotNull(customResultFields);
    }

    @Test
    public void testGetSections() {
        /**
         *Get section list
         * @param projectId
         * @param suiteId
         * @return SectionsList
         * */
        SectionsList sections = testRail.sections().list(pId, sId).execute();
        assertNotNull(sections);
        sections.getSections().forEach(section -> {
            assertNotNull(section.getId());
            assertNotNull(section.getName());
            assertNotNull(section.getSuiteId());
        });

        section = sections.getSections().get(0);
    }

    @Test(dependsOnMethods = {"testGetSections"})
    public void testGetSection() {
        /**
         * Get one section
         * @param suiteId
         * @return Section
         * */
        Section s = testRail.sections().get(section.getId()).execute();
        assertNotNull(s);
        assertNotNull(s.getId());
        assertNotNull(s.getName());
        assertNotNull(s.getSuiteId());
    }

    @Test(dependsOnMethods = {"testGetSections"})
    public void testAddSection() {
        /**
         * Create new section
         * @param Section
         * @return sectionId
         * */
        Section sectionBody = new Section();
        sectionBody.setName("test section");
        sectionBody.setSuiteId(sId);
        newSection = testRail.sections().add(pId, sectionBody).execute();
        assertNotNull(newSection);
        assertEquals(newSection.getName(), sectionBody.getName());
        assertNotNull(newSection.getId());
        assertNotNull(newSection.getName());
        assertNotNull(newSection.getSuiteId());
    }

    @Test(dependsOnMethods = {"testGetSections", "testAddSection", "testDeleteCaseAsList"})
    public void testDeleteSection() {
        /**
         * Create new section
         * @param Section
         * @return sectionId
         * */
        testRail.sections().delete(newSection.getId()).execute();
    }

    @Test(dependsOnMethods = {"testCustomCaseFields", "testAddSection"})
    public void testAddCase() {
        /**
         * Create new case
         * @param sectionId
         * @param Case
         * @param customCaseFields
         * @return case
         * */
        Case tCase = new Case();
        tCase.setTitle("new case");
        tCase.setSuiteId(sId);
        aCase = testRail.cases().add(newSection.getId(), tCase, customCaseFields).execute();
        assertNotNull(aCase);
        assertEquals(aCase.getTitle(), tCase.getTitle());
    }

    @Test(dependsOnMethods = {"testCustomCaseFields","testAddCase"})
    public void testDeleteCaseAsList() {
        /**
         * Delete list of cases
         * @param CasesList
         * @return CasesList
         * */

        List<Integer> ids = new ArrayList<>();
        ids.add(aCase.getId());

        CasesList l = new CasesList();
        l.setCaseIds(ids);
        testRail.cases().delete(l, sId).execute();
    }



}
