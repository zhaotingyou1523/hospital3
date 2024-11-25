package main.Java.com.hospital;


import java.util.Scanner;

public class MainClient {
    private static Scanner sc = new Scanner(System.in);
    private AppointmentClient appointmentClient = new AppointmentClient();
    private DepartmentCilent departmentCilent = new DepartmentCilent();
    private DoctorCilent doctorCilent = new DoctorCilent();
    private PatientClient patientClient = new PatientClient();

        public void start() {
            while (true) {
                System.out.println("=====환영합니다!仁爱 병원 정보 관리 시스템에 오신 것을 환영합니다.=====");
                System.out.println("1、과실 관리");
                System.out.println("2、의사 관리");
                System.out.println("3、환자 관리");
                System.out.println("4、예약 관리");
                System.out.println("5、의사 관리 - 의사 진료 일정 설정 (오늘과 앞으로 6일간의 진료 일정을 설정할 수 있습니다).");
                System.out.println("6、의사 관리 - 모든 의사의 진료 일정 상세 정보 표시 (현재와 앞으로 6일간의 진료 일정 포함).");
                System.out.println("7、의사 관리 - 진료 예약 및 등록.");
                System.out.println("8、특정 의사의 현재 및 향후 6일 간의 환자 예약 상세 정보 검색 (매일 예약된 환자의 구체적인 정보 표시).");
                System.out.println("실행할 명령어를 입력하세요：");
                switch (sc.next()) {
                    case "1":
                        departmentCilent.departmentManager();
                        break;
                    case "2":
                        doctorCilent.doctorManager();
                        break;
                    case "3":
                        patientClient.patientManager();
                        break;
                    case "4":
                        appointmentClient.appointmentManager();
                        break;
                    case "5":
                        break;
                    case "6":
                        break;
                    case "7":
                        break;
                    case "8":
                        break;
                    default:
                        System.out.println("입력하신 명령이 잘못되었습니다. 다시 입력해주세요！");
                }
            }
        }
    }
