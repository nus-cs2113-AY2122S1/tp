package taa.storage.deserializer;

//@@author leyondlee
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.teachingclass.TeachingClass;
import taa.student.Student;
import taa.student.StudentList;

import java.lang.reflect.Type;

public class TeachingClassDeserializer extends StorageDeserializer implements JsonDeserializer<TeachingClass> {
    private static final String MEMBER_ID = "id";
    private static final String MEMBER_NAME = "name";
    private static final String MEMBER_STUDENTLIST = "studentList";
    private static final String MEMBER_ASSESSMENTLIST = "assessmentList";
    private static final String[] COMPULSORY_MEMBERS = {MEMBER_ID, MEMBER_NAME};

    @Override
    public TeachingClass deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, COMPULSORY_MEMBERS)) {
            return null;
        }

        JsonElement idJson = jsonObject.get(MEMBER_ID);
        String id = idJson.getAsString();

        JsonElement nameJson = jsonObject.get(MEMBER_NAME);
        String name = nameJson.getAsString();

        TeachingClass teachingClass = new TeachingClass(id, name);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(StudentList.class, new StudentListDeserializer());
        gsonBuilder.registerTypeAdapter(AssessmentList.class, new AssessmentListDeserializer());
        Gson gson = gsonBuilder.create();

        JsonElement studentListJson = jsonObject.get(MEMBER_STUDENTLIST);
        if (studentListJson != null) {
            StudentList studentList = gson.fromJson(studentListJson, StudentList.class);
            if (studentList != null) {
                for (Student student : studentList.getStudents()) {
                    teachingClass.getStudentList().addStudent(student);
                }
            }
        }

        JsonElement assessmentListJson = jsonObject.get(MEMBER_ASSESSMENTLIST);
        if (assessmentListJson != null) {
            AssessmentList assessmentList = gson.fromJson(assessmentListJson, AssessmentList.class);
            if (assessmentList != null) {
                for (Assessment assessment : assessmentList.getAssessments()) {
                    teachingClass.getAssessmentList().addAssessment(assessment);
                }
            }
        }

        if (!teachingClass.verify()) {
            return null;
        }

        return teachingClass;
    }
}
