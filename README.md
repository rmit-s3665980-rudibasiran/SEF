# SEF
RMIT SEF Assignment

Collaborative effort to produce a Campus Management System

Methods & Scenarios
menuAction (int) [Admin]
What happens when an incorrect menu selection is passed in?
If a user is having a Student role, can he select addCourse menu?
What happens if a user account does/doesn't exist? 
Is the system extracting the correct role based on the userID?

viewCourse(courseCode, semester) [Admin]
What happens if a course does/doesn't exist? 
Should show Lecturers as well
Should show CourseOfferings as well

addCourse(courseCode, semester, preReq, cDesc) : Boolean [ProgramCoordinator]
What happens if a course already exists?
What happens when we remove a course which does/doesn't have students enrolled?
How do we enter pre-requisites?
How do we return pre-requisites? Boolean?

viewPerformance (studentID) : ArrayList <String> [Admin]
What happens if student does/doesn't exists?
What happens if student exists but there are no records, i.e., freshman?
Are the expected courses there?
Are the expected waivers there?

assignWaiver (studentID, courseCode, semester, Boolean): Boolean [ProgramCoordinator]
Assuming student exists, what happens when we add in exemptions which already exists?

changeLoad (studentID) : Boolean [ProgramCoordinator]
Assuming student exists, what happens when correct/incorrect values are passed in?
Assuming student exists, what if a student is already enrolled in 2 modules and the load is changed to 1?

assignGrade (studentID, courseCode, semester, grade) : Boolean [Lecturer]
Assuming student exists, what happens when we assign a grade to a module which the student is not enrolled in?
Assuming student exists, what happens when we assign a non-existent grade? RMIT grades are HD (High Distinction), DI (Distinction), C (Credit), PA (Passed), F (Failed).

addOffering (courseCode, semester, ttlStudent, active) : Boolean [ProgramCoordinator]
Assuming the course exists, what happens if we repeatedly add in the same course for the same semester?

addLecturer (courseCode, semester, Lecturer): Boolean [ProgramCoordinator]
Assuming both the lecturer and course exists, what happens if we repeatedly add in the same lecturer?
What happens when we remove a lecturer?

advanceWeek(courseCode, semester): Boolean [Admin]
What happens when we advance to a week which is not within the boundary 0-12?
What happens when we advance to week 12 and some of the grades are not in yet?
Can we advance all modules in the term to the same week instead of one-by-one?

enrolCourse (Student, CourseOffering) : Boolean [Student]
Assuming student and course exists, what happens if we add in a student/course which the student has already passed?
Assuming student and course exists, what happens if we add in a student/course which the student had failed?
Assuming student and course exists, what happens when pre-requisite checks fail?
Assuming student and course exists, what happens when pre-requisite checks pass?
Assuming student and course exists, what happens when waiver exists?
Assuming student and course exists, what happens when student exceeds load?

dropCourse(courseCode, semester): Boolean [Student]
Assuming student and course exists, what happens if it's already graded?

applyWaivers (studentID, courseCode, semester, reason) [Student]
Assuming student and course exists, what happens when we repeatedly add in the same waiver?

Driver.loadData
Check data is loaded properly

viewEligibleCourse(Student, semester)
