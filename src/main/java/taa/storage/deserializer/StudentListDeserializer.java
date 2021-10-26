package taa.storage.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import taa.student.Student;
import taa.student.StudentList;

import java.lang.reflect.Type;

public class StudentListDeserializer extends StorageDeserializer implements JsonDeserializer<StudentList> {
    private static final String MEMBER_STUDENTS = "students";
    private static final String[] COMPULSORY_MEMBERS = {MEMBER_STUDENTS};

    @Override
    public StudentList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        if (!hasMembers(jsonObject, COMPULSORY_MEMBERS)) {
            return null;
        }

        JsonElement studentsJson = jsonObject.get(MEMBER_STUDENTS);
        if (!studentsJson.isJsonArray()) {
            return null;
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Student.class, new StudentDeserializer());
        Gson gson = gsonBuilder.create();

        StudentList studentList = new StudentList();
        JsonArray studentsJsonArray = studentsJson.getAsJsonArray();
        for (JsonElement studentJson : studentsJsonArray) {
            Student student = gson.fromJson(studentJson, Student.class);
            if (student != null) {
                studentList.addStudent(student);
            }
        }

        if (!studentList.verify()) {
            return null;
        }

        return studentList;
    }
}
