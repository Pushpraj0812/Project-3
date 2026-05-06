package in.co.rays.project_3.controller;

/**
 * ORS View Provide Loose Coupling
 * 
 * @author Pushpraj Singh Kachhaway
 *
 */
public interface ORSView {

	public String APP_CONTEXT = "/project_3";

	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/ErrorView404.jsp";

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";

	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";

	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";

	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";

	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";

	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";

	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";

	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";

	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";

	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";

	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";

	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";

	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";

	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";

	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";

	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";

	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";

	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";

	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";

	public String TIMETABLE_VIEW = PAGE_FOLDER + "/TimeTableView.jsp";

	public String TIMETABLE_LIST_VIEW = PAGE_FOLDER + "/TimeTableListView.jsp";

	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";

	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";

	public String PRODUCT_VIEW = PAGE_FOLDER + "/ProductView.jsp";

	public String PRODUCT_LIST_VIEW = PAGE_FOLDER + "/ProductListView.jsp";

	public String SESSION_VIEW = PAGE_FOLDER + "/SessionView.jsp";

	public String SESSION_LIST_VIEW = PAGE_FOLDER + "/SessionListView.jsp";

	public String DISPATCH_VIEW = PAGE_FOLDER + "/DispatchView.jsp";

	public String DISPATCH_LIST_VIEW = PAGE_FOLDER + "/DispatchListView.jsp";

	public String TRANSPORT_VIEW = PAGE_FOLDER + "/TransportView.jsp";

	public String TRANSPORT_LIST_VIEW = PAGE_FOLDER + "/TransportListView.jsp";

	public String PODCAST_VIEW = PAGE_FOLDER + "/PodcastView.jsp";

	public String PODCAST_LIST_VIEW = PAGE_FOLDER + "/PodcastListView.jsp";

	public String TRAVEL_VIEW = PAGE_FOLDER + "/TravelView.jsp";

	public String TRAVEL_LIST_VIEW = PAGE_FOLDER + "/TravelListView.jsp";

	public String INSURANCE_VIEW = PAGE_FOLDER + "/InsuranceView.jsp";

	public String INSURANCE_LIST_VIEW = PAGE_FOLDER + "/InsuranceListView.jsp";

	public String STOCK_VIEW = PAGE_FOLDER + "/StockView.jsp";

	public String STOCK_LIST_VIEW = PAGE_FOLDER + "/StockListView.jsp";

	public String LABTEST_VIEW = PAGE_FOLDER + "/LabTestView.jsp";

	public String LABTEST_LIST_VIEW = PAGE_FOLDER + "/LabTestListView.jsp";

	public String MEDIA_COVERAGE_VIEW = PAGE_FOLDER + "/MediaCoverageView.jsp";

	public String MEDIA_COVERAGE_LIST_VIEW = PAGE_FOLDER + "/MediaCoverageListView.jsp";

	public String POLICY_VIEW = PAGE_FOLDER + "/PolicyView.jsp";

	public String POLICY_LIST_VIEW = PAGE_FOLDER + "/PolicyListView.jsp";

	public String TRANSFORMATION_VIEW = PAGE_FOLDER + "/TransformationView.jsp";

	public String TRANSFORMATION_LIST_VIEW = PAGE_FOLDER + "/TransformationListView.jsp";

	// Controller Mapping

	public String ERROR_CTL = APP_CONTEXT + "/ErrorCtl";

	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";

	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";

	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";

	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";

	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";

	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";

	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";

	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";

	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";

	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";

	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";

	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";

	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";

	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";

	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";

	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";

	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";

	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";

	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";

	public String TIMETABLE_CTL = APP_CONTEXT + "/ctl/TimeTableCtl";

	public String TIMETABLE_LIST_CTL = APP_CONTEXT + "/ctl/TimeTableListCtl";

	public String PRODUCT_CTL = APP_CONTEXT + "/ctl/ProductCtl";

	public String PRODUCT_LIST_CTL = APP_CONTEXT + "/ctl/ProductListCtl";

	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";

	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";

	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";

	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

	public String SESSION_CTL = APP_CONTEXT + "/ctl/SessionCtl";

	public String SESSION_LIST_CTL = APP_CONTEXT + "/ctl/SessionListCtl";

	public String DISPATCH_CTL = APP_CONTEXT + "/ctl/DispatchCtl";

	public String DISPATCH_LIST_CTL = APP_CONTEXT + "/ctl/DispatchListCtl";

	public String TRANSPORT_CTL = APP_CONTEXT + "/ctl/TransportCtl";

	public String TRANSPORT_LIST_CTL = APP_CONTEXT + "/ctl/TransportListCtl";

	public String PODCAST_CTL = APP_CONTEXT + "/ctl/PodcastCtl";

	public String PODCAST_LIST_CTL = APP_CONTEXT + "/ctl/PodcastListCtl";

	public String TRAVEL_CTL = APP_CONTEXT + "/ctl/TravelCtl";

	public String TRAVEL_LIST_CTL = APP_CONTEXT + "/ctl/TravelListCtl";

	public String INSURANCE_CTL = APP_CONTEXT + "/ctl/InsuranceCtl";

	public String INSURANCE_LIST_CTL = APP_CONTEXT + "/ctl/InsuranceListCtl";

	public String STOCK_CTL = APP_CONTEXT + "/ctl/StockCtl";

	public String STOCK_LIST_CTL = APP_CONTEXT + "/ctl/StockListCtl";

	public String LABTEST_CTL = APP_CONTEXT + "/ctl/LabTestCtl";

	public String LABTEST_LIST_CTL = APP_CONTEXT + "/ctl/LabTestListCtl";

	public String MEDIA_COVERAGE_CTL = APP_CONTEXT + "/ctl/MediaCoverageCtl";

	public String MEDIA_COVERAGE_LIST_CTL = APP_CONTEXT + "/ctl/LabTestListCtl";

	public String POLICY_CTL = APP_CONTEXT + "//ctl/PolicyCtl";

	public String POLICY_LIST_CTL = APP_CONTEXT + "/ctl/PolicyListCtl";

	public String TRANSFORMATION_CTL = APP_CONTEXT + "//ctl/TransformationCtl";

	public String TRANSFORMATION_LIST_CTL = APP_CONTEXT + "/ctl/TransformationListCtl";

}