package com.example.iutassistant.Extra;

import com.google.firebase.auth.FirebaseAuth;

public class Constant {
    /*Notification Channel ID */
    public static final String CHANNEL_ID = "notsch";

    //userInfo
    public static final String USER_INFO_SHARED_PREFERENCES = "info";

    public static final String ASSIGNED_CLASSES_SHARED_PREFERENCES = "assignedClasses";

    public static final String user_exists_preference = "Userinfo";
    public static final String username_preference = "name";
    public static final String uid_preference = "uid";
    public static final String user_profession_preference = "profession";
    public static final String user_email_preference = "email";
    public static final String user_email_extentio_preference = "email_extension";
    public static final String user_sid_preference = "sid";
    public static final String user_dept_preference = "dept";
    public static final String user_prog_preference = "prog";
    public static final String user_sec_preference = "sec";
    public static final String user_batch_preference = "batch";
    public static final String user_university_preference = "uni";


    // session preference keys
    public static final String USER_LOGIN_INFO_SHARED_PREFERENCES = "login";
    public static final String user_login_state_shared_preference = "loggedIn";

    //Courses shared preferences
    public static final String CLASSES_INFO_SHARED_PREFERENCES = "course_info";
    public static final String CLASSES_count_preference = "total_courses";


    //Firebase UsersInfo child node


    public static final String userInfo_node_name="name";
    public static final String userInfo_node_id="id";
    public static final String userInfo_node_sec="sec";
    public static final String userInfo_node_programme="prog";
    public static final String userInfo_node_dept="deptment";
    public static final String userInfo_node_uni="uni";
    public static final String userInfo_node_email="email";
    public static final String userInfo_node_profession="profession";



    public static final String IDENTITY_NODE="IDENTITY";
    //Database ref

    public static final String Ref="University/IUT";

    //Firebase Project Node

    public static final String Project_Structure_Root="ProjectStructure";
    public static final String Project_Node="Projects";
    public static final String Project_Course_Node="Courses";
    public static final String Project_Student_Node="Students";
    public static final String Project_Supervisor_Node="Supervisor";


    //project info

    public static final String Project_Name="projectName";
    public static final String Project_Course="course";
    public static final String Project_Supervisor_="supervisorMail";
    public static final String Project_Team_Mates="allSid";
    public static final String Project_Description="description";


    //removing substring from a mail id
    public static final String Remove_Com=".com";
    public static final String Remove_Edu=".edu";

    //Parent Node
    public static final String Course_Node="COURSES";
    public static final String Section_Node="SECTION";
    public static final String Teaches_Node="TEACHES";
    public static final String Teacher_Node="Teachers";
    public static final String Classes_Node="CLASSES";
    public static final String Class_Invitation_Node="Invitation";


    //status
    public static final String Status_Invitation="Invited";
    public static final String Project_Status_Requested_Node="Requested";
    public static final String Project_Status_Accepted_Node="Accepted";
    public static final String Project_Status_Rejected_Node="Rejected";


    //hints
    public static final String CLASS_FIREBASE_CONNECTOR="CLASS FIREBASE CONNECTOR";
    public static final String TEACHES_FIREBASE_CONNECTOR="TEACHES FIREBASE CONNECTOR";
    public static final String USER_SHARED_PREFERENCE_CONNECTOR="USER SP CONNECTOR";
    public static final String TEACHES_SHARED_PREFERENCE_CONNECTOR="TEACHES SP CONNECTOR";

    //path
    public static final String ASSIGNED_CLASSES_SHARED_PREFERENCE_PATH= FirebaseAuth.getInstance().getCurrentUser().getUid() +"classes";

    //Warning
    public static final String ALREADY_TAKEN_CLASSES_WARNING="-> contact with the email user to add yourseld a co-assistant teacher";













}
