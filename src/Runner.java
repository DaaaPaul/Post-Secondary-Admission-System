package src;

import java.util.Scanner;
import src.system.AdmissionSystem;

/**
 * Runner
 * Launches the Post-Secondary Admission System and provides a menu-driven
 * user interface for accessing system operations such as managing students,
 * institutions, programs, and applications.
 * Paul Peng and Rex Lin
 * Last modified 2026-06-13
 */
public class Runner {
	/**
	 * Starts the Post-Secondary Admission System and continuously displays
	 * the main menu until the user chooses to exit.
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		AdmissionSystem system = new AdmissionSystem();

		boolean running = true;

		while (running) {
			System.out.println("===== POST-SECONDARY ADMISSION SYSTEM =====");
			System.out.println("1. Add Student");
			System.out.println("2. Remove Student");
			System.out.println("3. Add Course to Student");
			System.out.println("4. Remove Course from Student");
			System.out.println("5. Add Extracurricular to Student");
			System.out.println("6. Remove Extracurricular from Student");
			System.out.println("7. Add Application");
			System.out.println("8. Remove Application");
			System.out.println("9. Add Institution");
			System.out.println("10. Remove Institution");
			System.out.println("11. Add Program to Institution");
			System.out.println("12. Remove Program from Institution");
			System.out.println("13. Search Student by Student ID");
			System.out.println("14. Search Student(s) by Name");
			System.out.println("15. Search Institution by Institution ID");
			System.out.println("16. Search Institution(s) by Name");
			System.out.println("17. Search Application by Application ID");
			System.out.println("18. Search Application(s) by Student");
			System.out.println("19. Search Application(s) by Program");
			System.out.println("20. Sort Students By Merit");
			System.out.println("21. Save Data");
			System.out.println("0. Exit");
			System.out.print("Enter option: ");

			int choice;
			try {
				choice = Integer.parseInt(in.nextLine());

			} catch (NumberFormatException e) {
				System.out.println("Invalid input.");
				continue;
			}

			switch (choice) {
				case 1: system.addStudent(); break;
				case 2: system.removeStudent(); break;
				case 3: system.addCourseToStudent(); break;
				case 4: system.removeCourseFromStudent(); break;
				case 5: system.addExtracurricularToStudent(); break;
				case 6: system.removeExtracurricularFromStudent(); break;
				case 7: system.addApplicationToStudentAndInstitution(); break;
				case 8: system.removeApplicationFromStudentAndInstitution(); break;
				case 9: system.addPostSecondary(); break;
				case 10: system.removePostSecondary(); break;
				case 11: system.addProgramToInstitution(); break;
				case 12: system.removeProgramFromInstitution(); break;
				case 13: system.searchStudentByIdUI(); break;
				case 14: system.searchStudentsByNameUI(); break;
				case 15: system.searchInstitutionByIdUI(); break;
				case 16: system.searchInstitutionsByNameUI(); break;
				case 17: system.searchApplicationByIdUI(); break;
				case 18: system.searchApplicationsByStudentUI(); break;
				case 19: system.searchApplicationsByProgramUI(); break;
				case 20: system.sortStudentsByMeritUI(); break;
				case 21: system.saveData(); System.out.println("Data saved successfully."); break;
				case 0: system.saveData(); System.out.println("Data saved. Exiting system."); running = false; break;
				default: System.out.println("Invalid input.");
			}
			System.out.println();
		}

		in.close();
		System.out.println("Goodbye.");
	}
}