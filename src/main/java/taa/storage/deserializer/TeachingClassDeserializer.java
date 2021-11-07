package taa.storage.deserializer;

//@@author leyondlee

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import taa.Parser;
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
        String id = getJsonElementAsString(idJson);

        JsonElement nameJson = jsonObject.get(MEMBER_NAME);
        String name = getJsonElementAsString(nameJson);
        if (name == null) {
            name = "";
        }

        if (!Parser.isValueValid(id) || !Parser.isValueValid(name)) {
            return null;
        }

        TeachingClass teachingClass = new TeachingClass(id, name);

        JsonElement studentListJson = jsonObject.get(MEMBER_STUDENTLIST);
        deserializeStudentList(studentListJson, teachingClass);

        JsonElement assessmentListJson = jsonObject.get(MEMBER_ASSESSMENTLIST);
        deserializeAssessmentList(assessmentListJson, teachingClass);

        if (!teachingClass.verify()) {
            return null;
        }

        return teachingClass;
    }

    private void deserializeStudentList(JsonElement studentListJson, TeachingClass teachingClass)
            throws JsonParseException {
        if (studentListJson == null) {
            return;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(StudentList.class, new StudentListDeserializer());
        Gson gson = gsonBuilder.create();

        StudentList deserializedStudentList = gson.fromJson(studentListJson, StudentList.class);
        if (deserializedStudentList == null) {
            return;
        }

        StudentList studentList = teachingClass.getStudentList();
        for (Student student : deserializedStudentList.getStudents()) {
            studentList.addStudent(student);
        }
    }

    private void deserializeAssessmentList(JsonElement assessmentListJson, TeachingClass teachingClass)
            throws JsonParseException {
        if (assessmentListJson == null) {
            return;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AssessmentList.class, new AssessmentListDeserializer());
        Gson gson = gsonBuilder.create();

        AssessmentList deserializedAssessmentList = gson.fromJson(assessmentListJson, AssessmentList.class);
        if (deserializedAssessmentList == null) {
            return;
        }

        AssessmentList assessmentList = teachingClass.getAssessmentList();
        for (Assessment assessment : deserializedAssessmentList.getAssessments()) {
            assessmentList.addAssessment(assessment);
        }
    }
}
