import urllib.request
import urllib.parse
import json
import random

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
    print("Starting Maharashtra-based database seeding...")

    # Maharashtra Regions
    regions_data = [
        {"name": "Pune Region", "code": "PUNE"},
        {"name": "Mumbai Region", "code": "MUM"},
        {"name": "Nagpur Region", "code": "NAG"},
        {"name": "Nashik Region", "code": "NSK"}
    ]

    # Student name components for random combinations
    first_names = ["Rahul", "Sneha", "Aditya", "Pooja", "Rohan", "Sayali", "Amit", "Tejashree", "Ketan", "Anjali", "Sarang", "Prachi", "Vikram", "Shreya", "Gaurav", "Minal"]
    middle_names = ["Sanjay", "Ramesh", "Vijay", "Anil", "Arjun", "Dilip", "Prakash", "Sunil", "Vilas", "Sudhir"]
    last_names = ["Joshi", "Patil", "Deshmukh", "Kulkarni", "Chavan", "Shinde", "Pawar", "More", "Gaikwad", "Tambe", "Bhosle", "Jadhav"]

    for r_idx, r_info in enumerate(regions_data):
        print(f"\n--- Seeding Region: {r_info['name']} ---")
        region = post_request("/regions", {"regionName": r_info['name']})
        if not region:
            print(f"Failed to seed region {r_info['name']}. Server must be running on {BASE_URL}")
            return
        
        region_id = region.get("regionId")
        print(f"Created Region {r_info['name']} (ID: {region_id})")

        # 2 Exam Centres per Region
        for ec_idx in range(1, 3):
            centre_code = f"EC-{r_info['code']}-{ec_idx:02d}"
            
            # Custom names for exam centres
            if r_info['code'] == "PUNE":
                centre_name = "Pune Central Centre" if ec_idx == 1 else "Pune East Centre"
            elif r_info['code'] == "MUM":
                centre_name = "Mumbai South Centre" if ec_idx == 1 else "Mumbai North Centre"
            elif r_info['code'] == "NAG":
                centre_name = "Nagpur City Centre" if ec_idx == 1 else "Nagpur West Centre"
            else:
                centre_name = "Nashik Central Centre" if ec_idx == 1 else "Nashik Road Centre"

            centre = post_request(
                "/exam-centres",
                {"centreCode": centre_code, "centreName": centre_name},
                params={"regionId": region_id}
            )
            if not centre:
                print(f"Failed to create Exam Centre {centre_code}")
                continue
            
            centre_id = centre.get("centreId")
            print(f"  Created Exam Centre {centre_name} (ID: {centre_id})")

            # 5 Schools per Exam Centre
            for sch_idx in range(1, 6):
                school_code = f"SCH-{r_info['code']}-{ec_idx}-{sch_idx}"
                
                # Assign school name prefixes
                school_types = ["Vidyalaya", "High School", "Academy", "Public School", "English Medium School"]
                school_name = f"{centre_name.split()[0]} {school_types[sch_idx-1]} {sch_idx}"
                
                school_payload = {
                    "schoolName": school_name,
                    "schoolCode": school_code,
                    "boardAffiliation": "State Board" if sch_idx % 2 == 0 else "CBSE",
                    "mediumOfInstruction": "English" if sch_idx != 2 else "Marathi",
                    "establishmentYear": 1990 + (r_idx * 5) + sch_idx,
                    "principalName": f"Mr. {random.choice(first_names)} {random.choice(last_names)}",
                    "principalContactNumber": f"98200{r_idx}{ec_idx}{sch_idx}00",
                    "officialEmail": f"info@{school_code.lower().replace('-', '_')}.edu.in",
                    "seatingCapacity": 250 + (sch_idx * 15),
                    "numberOfClassrooms": 12 + sch_idx,
                    "cctvAvailable": True,
                    "address": {
                        "line1": f"Plot No {sch_idx * 10}, Main Road",
                        "line2": f"Near {centre_name.split()[0]} Station",
                        "villageOrCity": centre_name.split()[0],
                        "taluka": centre_name.split()[0],
                        "district": f"{centre_name.split()[0]} District",
                        "state": "Maharashtra",
                        "pincode": f"4110{r_idx}{ec_idx}"
                    }
                }
                
                school = post_request("/schools", school_payload, params={"centreId": centre_id})
                if not school:
                    print(f"    Failed to create School {school_name}")
                    continue
                
                school_id = school.get("schoolId")
                print(f"    Created School {school_name} (ID: {school_id})")

                # 10 Students per School
                for stu_idx in range(1, 11):
                    # Pick unique names for logs/clarity
                    f_name = first_names[(stu_idx - 1) % len(first_names)]
                    m_name = middle_names[stu_idx % len(middle_names)]
                    l_name = last_names[(stu_idx + 1) % len(last_names)]
                    
                    student_payload = {
                        "firstName": f_name,
                        "middleName": m_name,
                        "lastName": l_name,
                        "contact": f"91234{r_idx}{ec_idx}{sch_idx}{stu_idx:02d}",
                        "email": f"{f_name.lower()}.{l_name.lower()}_{r_info['code'].lower()}_{ec_idx}_{sch_idx}_{stu_idx}@example.com",
                        "password": "student123",
                        "age": 14 + (stu_idx % 3),
                        "motherTongue": "Marathi"
                    }
                    
                    student = post_request("/students", student_payload, params={"schoolId": school_id})
                    if not student:
                        print(f"      Failed to create Student {student_payload['email']}")
                        continue
                    
                    print(f"      Created Student: {student.get('firstName')} {student.get('lastName')} (ID: {student.get('studentId')})")

    print("\nDatabase seeding completed successfully!")

if __name__ == "__main__":
    main()
