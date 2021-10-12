package taa.storage.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.module.Module;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

import java.lang.reflect.Type;

public class ModuleDeserializer extends StorageDeserializer implements JsonDeserializer<Module> {
    private static final String MEMBER_CODE = "code";
    private static final String MEMBER_NAME = "name";
    private static final String MEMBER_LESSONCOUNT = "lessonCount";
    private static final String MEMBER_STUDENTLIST = "studentList";
    private static final String MEMBER_ASSESSMENTLIST = "assessmentList";
    private static final String[] MEMBERS = {
        MEMBER_CODE,
        MEMBER_NAME,
        MEMBER_LESSONCOUNT,
        MEMBER_STUDENTLIST,
        MEMBER_ASSESSMENTLIST
    };

    @Override
    public Module deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, MEMBERS)) {
            return null;
        }

        JsonElement codeJson = jsonObject.get(MEMBER_CODE);
        String code = codeJson.getAsString();

        JsonElement nameJson = jsonObject.get(MEMBER_NAME);
        String name = nameJson.getAsString();

        JsonElement lessonCountJson = jsonObject.get(MEMBER_LESSONCOUNT);
        if (!Util.isStringInteger(lessonCountJson.getAsString())) {
            return null;
        }
        int lessonCount = lessonCountJson.getAsInt();

        Module module = new Module(code, name);
        module.setLessonCount(lessonCount);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(StudentList.class, new StudentListDeserializer());
        gsonBuilder.registerTypeAdapter(AssessmentList.class, new AssessmentListDeserializer());
        Gson gson = gsonBuilder.create();

        JsonElement studentListJson = jsonObject.get(MEMBER_STUDENTLIST);
        StudentList studentList = gson.fromJson(studentListJson, StudentList.class);
        for (Student student : studentList.getStudents()) {
            module.getStudentList().addStudent(student);
        }

        JsonElement assessmentListJson = jsonObject.get(MEMBER_ASSESSMENTLIST);
        AssessmentList assessmentList = gson.fromJson(assessmentListJson, AssessmentList.class);
        for (Assessment assessment : assessmentList.getAssessments()) {
            module.getAssessmentList().addAssessment(assessment);
        }

        if (!module.verify()) {
            return null;
        }

        return module;
    }
}
