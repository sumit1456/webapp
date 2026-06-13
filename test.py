import urllib.request
import urllib.parse
import json

BASE_URL = "http://localhost:8080"

def post_request(endpoint, data=None, params=None):
    url = f"{BASE_URL}{endpoint}"
    if params:
        url += "?" + urllib.parse.urlencode(params)
    
    headers = {"Content-Type": "application/json"}
    req_body = json.dumps(data).encode("utf-8") if data else b""
    
    req = urllib.request.Request(url, data=req_body, headers=headers, method="POST")
    try:
        with urllib.request.urlopen(req) as response:
            res_body = response.read().decode("utf-8")
            return json.loads(res_body)
    except Exception as e:
        print(f"Error calling {url}: {e}")
        return None

def main():
    print("Starting API-based database seeding...")

    # 1. Create Regions
    print("\n--- Seeding Regions ---")
    maharashtra = post_request("/regions", {"regionName": "Maharashtra"})
    delhi = post_request("/regions", {"regionName": "Delhi"})
    karnataka = post_request("/regions", {"regionName": "Karnataka"})

    if not maharashtra or not delhi:
        print("Failed to seed regions. Make sure the Spring Boot server is running on http://localhost:8080.")
        return

    print(f"Maharashtra Region Created. ID: {maharashtra.get('regionId')}")
    print(f"Delhi Region Created. ID: {delhi.get('regionId')}")

    # 2. Create Exam Centres
    print("\n--- Seeding Exam Centres ---")
    mumbai_centre = post_request(
        "/exam-centres", 
        {"centreCode": "MC01", "centreName": "Mumbai Central Exam Centre"},
        params={"regionId": maharashtra.get("regionId")}
    )
    pune_centre = post_request(
        "/exam-centres", 
        {"centreCode": "PC02", "centreName": "Pune City Exam Centre"},
        params={"regionId": maharashtra.get("regionId")}
    )
    delhi_centre = post_request(
        "/exam-centres", 
        {"centreCode": "DC01", "centreName": "Delhi Central Exam Centre"},
        params={"regionId": delhi.get("regionId")}
    )

    if not mumbai_centre or not pune_centre:
        print("Failed to seed exam centres.")
        return

    print(f"Mumbai Exam Centre Created. ID: {mumbai_centre.get('centreId')}")
    print(f"Pune Exam Centre Created. ID: {pune_centre.get('centreId')}")

    # 3. Create Schools
    print("\n--- Seeding Schools ---")
    mumbai_school_payload = {
        "schoolName": "Mumbai High School",
        "schoolCode": "MHS101",
        "boardAffiliation": "CBSE",
        "mediumOfInstruction": "English",
        "establishmentYear": 1995,
        "principalName": "Dr. R. K. Sharma",
        "principalContactNumber": "9820098200",
        "officialEmail": "info@mumbaihigh.edu.in",
        "seatingCapacity": 300,
        "numberOfClassrooms": 15,
        "cctvAvailable": True,
        "address": {
            "line1": "123 MG Road",
            "line2": "Near Metro Station",
            "villageOrCity": "Mumbai",
            "taluka": "Mumbai",
            "district": "Mumbai City",
            "state": "Maharashtra",
            "pincode": "400001"
        }
    }
    mumbai_school = post_request("/schools", mumbai_school_payload, params={"centreId": mumbai_centre.get("centreId")})

    pune_school_payload = {
        "schoolName": "Pune Academy",
        "schoolCode": "PA202",
        "boardAffiliation": "State Board",
        "mediumOfInstruction": "English/Marathi",
        "establishmentYear": 2002,
        "principalName": "Mrs. Anjali Patil",
        "principalContactNumber": "9850098500",
        "officialEmail": "contact@puneacademy.org",
        "seatingCapacity": 250,
        "numberOfClassrooms": 10,
        "cctvAvailable": True,
        "address": {
            "line1": "45 FC Road",
            "line2": "Shivajinagar",
            "villageOrCity": "Pune",
            "taluka": "Pune",
            "district": "Pune",
            "state": "Maharashtra",
            "pincode": "411005"
        }
    }
    pune_school = post_request("/schools", pune_school_payload, params={"centreId": pune_centre.get("centreId")})

    if not mumbai_school or not pune_school:
        print("Failed to seed schools.")
        return

    print(f"Mumbai School Created. ID: {mumbai_school.get('schoolId')}")
    print(f"Pune School Created. ID: {pune_school.get('schoolId')}")

    # 4. Create Students
    print("\n--- Seeding Students ---")
    student_1 = post_request(
        "/students",
        {
            "firstName": "Rahul",
            "middleName": "Kumar",
            "lastName": "Singh",
            "contact": "9876543210",
            "email": "rahul@example.com",
            "password": "student123",
            "age": 16,
            "motherTongue": "Hindi"
        },
        params={"schoolId": mumbai_school.get("schoolId")}
    )

    student_2 = post_request(
        "/students",
        {
            "firstName": "Priya",
            "middleName": "Suresh",
            "lastName": "Patil",
            "contact": "9123456789",
            "email": "priya@example.com",
            "password": "student123",
            "age": 15,
            "motherTongue": "Marathi"
        },
        params={"schoolId": pune_school.get("schoolId")}
    )

    if student_1:
        print(f"Student 1 Created: {student_1.get('firstName')} (ID: {student_1.get('studentId')})")
    if student_2:
        print(f"Student 2 Created: {student_2.get('firstName')} (ID: {student_2.get('studentId')})")

    # 5. Create Exams
    print("\n--- Seeding Exams ---")
    exam_1 = post_request("/exams", {
        "exam_name": "Prabodh Hindi Exam",
        "exam_code": "PRABODH_2026",
        "no_of_papers": 2,
        "exam_fees": 500.0,
        "papers": "[{\"code\":\"P1\",\"name\":\"Paper I\",\"maxMarks\":100},{\"code\":\"P2\",\"name\":\"Paper II\",\"maxMarks\":100}]",
        "exam_details": "{\"description\":\"Basic entry level Hindi exam for non-Hindi speakers.\",\"passingPercentage\":35}",
        "application_start_date": "2026-06-08",
        "application_end_date": "2026-07-03",
        "exam_start_date": "2026-07-13",
        "exam_end_date": "2026-07-15",
        "status": "PUBLISHED"
    })

    exam_2 = post_request("/exams", {
        "exam_name": "Praveen Hindi Exam",
        "exam_code": "PRAVEEN_2026",
        "no_of_papers": 2,
        "exam_fees": 750.0,
        "papers": "[{\"code\":\"P1\",\"name\":\"Paper I\",\"maxMarks\":100},{\"code\":\"P2\",\"name\":\"Paper II\",\"maxMarks\":100}]",
        "exam_details": "{\"description\":\"Advanced level Hindi exam.\",\"passingPercentage\":40}",
        "application_start_date": "2026-06-10",
        "application_end_date": "2026-07-05",
        "exam_start_date": "2026-07-15",
        "exam_end_date": "2026-07-17",
        "status": "PUBLISHED"
    })

    if exam_1:
        print(f"Exam 1 Created: {exam_1.get('exam_name')} (ID: {exam_1.get('examNo')})")
    if exam_2:
        print(f"Exam 2 Created: {exam_2.get('exam_name')} (ID: {exam_2.get('examNo')})")

    print("\nDatabase seeding completed successfully!")

if __name__ == "__main__":
    main()
