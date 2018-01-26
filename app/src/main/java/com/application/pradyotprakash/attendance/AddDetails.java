package com.application.pradyotprakash.attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AddDetails extends AppCompatActivity {

    private Spinner branchesSpinner, semesterSpinner, subjectSpinner, teacherTypeSpinner;
    List<String> list;
    TextView classHeader, branchHeaderSubject, subjectsHeaderSubject;
    EditText className;
    String branchSelected, semesterSelected, subjectSelected, teacherTypeSelected, classNameString;
    Button add, addStudent;
    private DatabaseReference mFirebaseDatabase;
    String uid, tUserName;
    public int number, numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        branchesSpinner = findViewById(R.id.branchOption);
        subjectSpinner = findViewById(R.id.subjectsOption);
        semesterSpinner = findViewById(R.id.semesterOptions);
        teacherTypeSpinner = findViewById(R.id.teacherTypeOptions);
        add = findViewById(R.id.addInforamtion);
        addStudent = findViewById(R.id.addStudent);
        className = findViewById(R.id.classNames);
        classHeader = findViewById(R.id.classSemester);
        branchHeaderSubject = findViewById(R.id.branches);
        subjectsHeaderSubject = findViewById(R.id.subjects);
        addStudent.setEnabled(false);
        FirebaseUser mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = mCurrentUser.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Teacher").child("SubjectTeacher").child(uid);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tUserName = dataSnapshot.child("Name").getValue().toString();
                number = Integer.parseInt(dataSnapshot.child("Number").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Unable to fetch the data right now!", Toast.LENGTH_SHORT).show();
            }
        });
        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String branch = String.valueOf(branchesSpinner.getSelectedItem());
                String semester = String.valueOf(semesterSpinner.getSelectedItem());
                if (branch.contentEquals("Civil Engineering")) {
                    if (semester.contentEquals("Semester 3")) {
                        list = new ArrayList<>();
                        list.add("Basic Surveying");
                        list.add("Basic Surveying Practice");
                        list.add("Building Construction and Pre Fabrication");
                        list.add("building Materials and Construction");
                        list.add("Engineering Geology");
                        list.add("Engineering Earth Science and Materials");
                        list.add("Fluids Mechanics");
                        list.add("Geodetic Engineering");
                        list.add("Geodetic Engineering Laboratory");
                        list.add("Intellectual Property Rights");
                        list.add("Materials Testing Laboratory");
                        list.add("Mechanics of Fluids");
                        list.add("Rural Water Supply and Sanitation");
                        list.add("Rural Urban Planning and Architecture");
                        list.add("Strength of Materials");
                        list.add("Strength of Materials Laboratory");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 4")) {
                        list = new ArrayList<>();
                        list.add("Advanced Geodetic Engineering");
                        list.add("Air Pollution and Control");
                        list.add("Alternative Building Materials");
                        list.add("Advanced Surveying");
                        list.add("Analysis of Determinate Structures");
                        list.add("Applied Hydraulics");
                        list.add("Basic of Geotechnical Engineering");
                        list.add("Concrete Technology");
                        list.add("Engineering Earth Science and Materials");
                        list.add("Engineering Geology Laboratory");
                        list.add("Fluid Mechanics and Hydraulic Machines Laboratory");
                        list.add("Mechanics of Fluids Laboratory");
                        list.add("Programming in C++");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 5")) {
                        list = new ArrayList<>();
                        list.add("Air Pollution and Control - Elective - 1");
                        list.add("Analysis of Indeterminate Structures");
                        list.add("Applied Geotechnical Engineering");
                        list.add("Computer Aided Building Planning and Drawing");
                        list.add("Concrete and Highway Materials Laboratory");
                        list.add("Design of RC Structural Elements");
                        list.add("Geotechnical Engineering");
                        list.add("Masonry Structures - Elective");
                        list.add("Occupation Health and Safety - Open Elective");
                        list.add("Railways, Harbour, Tunneling & Airports - Elective");
                        list.add("Remote Sensing and GIS - Open Elective");
                        list.add("Sustainability Concepts in Engineering - Open Elective");
                        list.add("Theory of Elasticity - Elective");
                        list.add("Traffic Engineering - Open Elective - 1");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 6")) {
                        list = new ArrayList<>();
                        list.add("Alternative Building Materials");
                        list.add("Construction Management and Entrepreneurship");
                        list.add("Design of Steel Structural Elements");
                        list.add("Environmental Protection and Management");
                        list.add("Extensive Survey Project/Camp");
                        list.add("Finite Element Method");
                        list.add("Ground Improvement Techniques");
                        list.add("Highway Engineering");
                        list.add("Matrix Method of Structural Analysis");
                        list.add("Numerical Methods and Applications");
                        list.add("Software Application Lab");
                        list.add("Solid Waste Management");
                        list.add("Water Resource Management");
                        list.add("Water Supply and Treatment Engineering");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    }
                } else if (branch.contentEquals("Computer Science Engineering")) {
                    if (semester.contentEquals("Semester 3")) {
                        list = new ArrayList<>();
                        list.add("Analog and Digital Electronics");
                        list.add("Analog And Digital Electronics Laboratory");
                        list.add("Computer Organization");
                        list.add("Data Structure and Applications");
                        list.add("Data Structures and Applications");
                        list.add("Data Structures with C Laboratory");
                        list.add("Design of Programming with Logic");
                        list.add("Discrete Mathematical Structures");
                        list.add("Engineering Mathematics - III");
                        list.add("Introduction to Web Development");
                        list.add("Probability and Statistics");
                        list.add("Unix and SHELL Programming");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 4")) {
                        list = new ArrayList<>();
                        list.add("Data Communication");
                        list.add("Design and Analysis of Algorithm Laboratory");
                        list.add("Design and Analysis of Algorithms");
                        list.add("Engineering Mathematics - IV");
                        list.add("Graph Theory And Its Applications");
                        list.add("Introduction to Cyber Security and Cyber Law");
                        list.add("Microprocessor and Microcontroller Laboratory");
                        list.add("Microprocessors and ARM Processor");
                        list.add("Microprocessors and Microcontrollers");
                        list.add("Microprocessors and ARM Laboratory");
                        list.add("Object Oriented Concepts");
                        list.add("Object Oriented Programming with C++");
                        list.add("Parallel Programming with OpenMP");
                        list.add("Python Programming");
                        list.add("Software Engineering");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 5")) {
                        list = new ArrayList<>();
                        list.add(".Net Framework for Application Development (Elective)");
                        list.add("Advanced Algorithms");
                        list.add("Advanced JAVA and J2EE");
                        list.add("Artificial Intelligence (Open Elective)");
                        list.add("Automata Theory and Computability");
                        list.add("Cloud Computing (Open Elective)");
                        list.add("Computer Networks");
                        list.add("Computer Network Laboratory");
                        list.add("Database Management System");
                        list.add("DBMS Lab with Mini Project");
                        list.add("Embedded Computing System");
                        list.add("Introduction to Software Testing");
                        list.add("Management, Entrepreneurship and Cyber Law");
                        list.add("Mobile Application Development");
                        list.add("Object Oriented Modeling and Design");
                        list.add("Programming in JAVA (Open Elective)");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 6")) {
                        list = new ArrayList<>();
                        list.add("Big Data Analytics");
                        list.add("Computer Graphics and Visualization");
                        list.add("Computer Graphics Laboratory with Mini Project");
                        list.add("Cryptography, Network, Security and Cyber Law");
                        list.add("Data Mining and Data Warehouse");
                        list.add("Distributed Computing System");
                        list.add("Mobile Application Development");
                        list.add("Multi-Core Architecture and Programming");
                        list.add("Operation Systems");
                        list.add("Operation Research");
                        list.add("Python Application Programming");
                        list.add("Service Oriented Architecture");
                        list.add("Software Architecture and Design Patterns");
                        list.add("System Software and Compiler Design");
                        list.add("System Software and Operating System Laboratory");
                        list.add("Wireless Networks and Mobile Computing");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    }
                } else if (branch.contentEquals("Electrical & Electronics Engineering")) {
                    if (semester.contentEquals("Semester 3")) {
                        list = new ArrayList<>();
                        list.add("Analog Electronics Circuits");
                        list.add("Communication Systems (Elective)");
                        list.add("Digital System Design");
                        list.add("Electronic Circuit Analysis");
                        list.add("Electrical and Electronic Measurement (Elective)");
                        list.add("Electrical Engineering Materials (Elective)");
                        list.add("Electrical Machines Laboratory - 1");
                        list.add("Electronics Laboratory");
                        list.add("Power Generation and Economics (Elective)");
                        list.add("Transformers and Generators");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 4")) {
                        list = new ArrayList<>();
                        list.add("Operations Research (Elective)");
                        list.add("Electric Motors");
                        list.add("Electrical Machines Laboratory - 2");
                        list.add("Electromagnetic Field Theory");
                        list.add("Fundamentals of HDL (Elective)");
                        list.add("Generation, Transmission and Distribution");
                        list.add("Instrumentation Engineering (Elective)");
                        list.add("OP-AMP and Linear ICS Laboratory");
                        list.add("Operational Amplifiers and Linear ICs");
                        list.add("Renewable Energy (Elective)");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 5")) {
                        list = new ArrayList<>();
                        list.add("Electrical Engineering Materials (Elective)");
                        list.add("Management and Entrepreneurship");
                        list.add("Microcontroller");
                        list.add("Microcontroller Laboratory - 1");
                        list.add("Power Electronics");
                        list.add("Power Electronics Laboratory");
                        list.add("Sensor and Transducers (Professional Elective)");
                        list.add("Signals and Systems");
                        list.add("Solar & Wind Energy (Professional Elective)");
                        list.add("Special Electrical Machines (Elective)");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 6")) {
                        list = new ArrayList<>();
                        list.add("Advanced power Electronics (Professional Elective)");
                        list.add("Artificial Neural Networks & Fuzzy Logic (Elective)");
                        list.add("Batteries Fuel Cells for Commercial Military Space Application ");
                        list.add("Computer Aided Electrical Drawing (Elective)");
                        list.add("Control System Laboratory");
                        list.add("Control Systems");
                        list.add("Digital Signal Processing");
                        list.add("Digital Signal Processing Laboratory");
                        list.add("Electrical Machine Design");
                        list.add("Energy Audit & Demand Side Management (Elective)");
                        list.add("Industrial Servo Control Systems (Elective)");
                        list.add("Power System Analysis - 1");
                        list.add("Sensors and Transducers (Open Elective)");
                        list.add("Solar & Wind Energy (Professional Elective)");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    }
                } else if (branch.contentEquals("Electronics & Communication Engineering")) {
                    if (semester.contentEquals("Semester 3")) {
                        list = new ArrayList<>();
                        list.add("Analog Electronic Laboratory");
                        list.add("Electronics Instrumentation");
                        list.add("Analog Electronics");
                        list.add("Computer Organization (Elective)");
                        list.add("Design and Analysis of Algorithms (Elective)");
                        list.add("Digital Electronics");
                        list.add("Digital Electronics Laboratory");
                        list.add("Digital Electronics and Interfacing Laboratory");
                        list.add("Engineering Electromagnetics");
                        list.add("Microprocessor and Microcontrollers");
                        list.add("Network Analysis");
                        list.add("OOPs with C++ (Elective)");
                        list.add("Operating Systems (Elective)");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 4")) {
                        list = new ArrayList<>();
                        list.add("Microcontrollers for Embedded Systems");
                        list.add("Business Communication (Elective)");
                        list.add("Electronics Engineering Materials (Elective)");
                        list.add("Engineering Electromagnetics");
                        list.add("Linear Integrated Circuits");
                        list.add("Linear Integrated Circuits Laboratory");
                        list.add("MEMS and Microsystems (Elective)");
                        list.add("Microcontrollers and Embedded Systems Laboratory");
                        list.add("Network Analysis and Control Systems");
                        list.add("Virtual Instrumentation (Elective)");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 5")) {
                        list = new ArrayList<>();
                        list.add("8051 Microcontrollers (Open Elective)");
                        list.add("Automotive Electronics (Open Elective)");
                        list.add("Digital Signal Processing");
                        list.add("Electrical Engineering Materials");
                        list.add("HDL Labs");
                        list.add("Information Theory and Coding");
                        list.add("L: Digital Signal Processing Lab");
                        list.add("Management and Entrepreneurship");
                        list.add("Nanoelectronics");
                        list.add("Operating System");
                        list.add("Object Oriented Programming Using C++ (Open Elective)");
                        list.add("Switching & Finite Automata Theory");
                        list.add("Verilog HDL");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 6")) {
                        list = new ArrayList<>();
                        list.add("Artificial Neural Networks");
                        list.add("ARM Microcontroller & Embedded System");
                        list.add("Adaptive Signal Processing");
                        list.add("Cellular Mobile Communications");
                        list.add("Computer Communication Networks");
                        list.add("Computer Networks Laboratory");
                        list.add("Digital Communication");
                        list.add("Digital Switching Systems");
                        list.add("Embedded Controller Lab");
                        list.add("Microelectronics");
                        list.add("VLSI Design");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    }
                } else if (branch.contentEquals("Information Science & Engineering")) {
                    if (semester.contentEquals("Semester 3")) {
                        list = new ArrayList<>();
                        list.add("Engineering Mathematics - III");
                        list.add("Electronics Circuits");
                        list.add("Logic Design");
                        list.add("Discrete Mathematical Structures");
                        list.add("Data Structures with C");
                        list.add("OOPs with C++");
                        list.add("DS with C/C++ Lab");
                        list.add("EC & LD Lab");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 4")) {
                        list = new ArrayList<>();
                        list.add("Engineering Mathematics - IV");
                        list.add("Graph Theory and Combinatorics");
                        list.add("Design and Analysis of Algorithms");
                        list.add("Unix and Shell Programming");
                        list.add("Microprocessors");
                        list.add("Computer Organization");
                        list.add("Design and Analysis of Algorithm Laboratory");
                        list.add("Microprocessors Laboratory");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 5")) {
                        list = new ArrayList<>();
                        list.add(".Net Framework for Application Development");
                        list.add("Advanced JAVA and J2EE");
                        list.add("Automata Theory and Computability");
                        list.add("Artificial Intelligence");
                        list.add("Cloud Computing (Open Elective)");
                        list.add("Computer Networks");
                        list.add("Computer Network Laboratory");
                        list.add("Database Management System");
                        list.add("DBMS Lab with Mini Project");
                        list.add("Embedded Computing System");
                        list.add("Management, Entrepreneurship and Cyber Law");
                        list.add("Mobile Application Development");
                        list.add("Object Oriented Modeling and Design");
                        list.add("Programming Languages");
                        list.add("Social Network Analysis");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 6")) {
                        list = new ArrayList<>();
                        list.add("Cryptography, Network, Security and Cyber Law");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    }
                } else if (branch.contentEquals("Mechanical Engineering")) {
                    if (semester.contentEquals("Semester 3")) {
                        list = new ArrayList<>();
                        list.add("Basic Thermodynamics");
                        list.add("Computer Aided Machine Drawing");
                        list.add("Database Management Systems");
                        list.add("Fluid Mechanics");
                        list.add("Foundry and Forging Lab");
                        list.add("Machine Shop");
                        list.add("Machine Shop Laboratory");
                        list.add("Machine Tools and Operations");
                        list.add("Material Science");
                        list.add("Material Testing Laboratory");
                        list.add("Mechanical Measurements & Metrology");
                        list.add("Mechanical Measurements & Metrology Lab");
                        list.add("Mechanics of Materials");
                        list.add("Metal Casting and Welding");
                        list.add("Nano Science");
                        list.add("Non-Conventional Energy Sciences");
                        list.add("Smart Materials");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 4")) {
                        list = new ArrayList<>();
                        list.add("Applied Thermodynamics");
                        list.add("Biomass Energy Systems");
                        list.add("Computer Aided Machine Drawing");
                        list.add("Energy Laboratory");
                        list.add("Engineering Design");
                        list.add("Foundry and Forging Lab");
                        list.add("Fluid Mechanics");
                        list.add("Kinematics of Machinery");
                        list.add("Kinematics of Machines");
                        list.add("Machine Shop");
                        list.add("Machine Tools and Operations");
                        list.add("Management Information Systems");
                        list.add("Materials Testing Lab");
                        list.add("Mechanical Measurements and Metrology");
                        list.add("Mechanical Measurements and Metrology Lab");
                        list.add("Mechanical Measurements Laboratory");
                        list.add("Metal Casting and Welding");
                        list.add("Mechanics of Materials");
                        list.add("Organizational Behavior & Professional Communication");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 5")) {
                        list = new ArrayList<>();
                        list.add("Automation and Robotics (Open Elective - 1)");
                        list.add("Design of Machine Elements - 1");
                        list.add("Dynamics of Machinery");
                        list.add("Energy and Environmental (Open Elective - 1)");
                        list.add("Energy Lab");
                        list.add("Fluid Mechanics & Machinery Lab");
                        list.add("Human Resource Management (Elective - 1)");
                        list.add("Management and Engineering Economics");
                        list.add("Non Traditional Machining (Elective -1)");
                        list.add("Optimization Techniques (Open Elective -1)");
                        list.add("Project Management (Open Elective - 1)");
                        list.add("Refrigeration & Air-Conditioning (Elective - 1)");
                        list.add("Theory of Elasticity (Elective - 1)");
                        list.add("Turbo Machines");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    } else if (semester.contentEquals("Semester 6")) {
                        list = new ArrayList<>();
                        list.add("Automobile Engineering");
                        list.add("Computational Fluid Dynamics");
                        list.add("Computer Integrated Manufacturing");
                        list.add("Design of Machine Elements II");
                        list.add("Energy Auditing");
                        list.add("Finite Element Analysis");
                        list.add("Heat Transfer");
                        list.add("Heat Transfer Lab");
                        list.add("Industrial Safety");
                        list.add("Mechanics of Composite Materials");
                        list.add("Metal Forming");
                        list.add("Maintenance Engineering");
                        list.add("Modeling and Analysis Lab (FEA)");
                        list.add("Tool Design");
                        list.add("Total Quality Management");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                                android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        dataAdapter.notifyDataSetChanged();
                        subjectSpinner.setAdapter(dataAdapter);
                    }
                }
                if (semester.contentEquals("Semester 1 & 2")) {
                    list = new ArrayList<>();
                    list.add("Basic Electrical Engineering");
                    list.add("Basic Electronics");
                    list.add("Computer Aided Computer Drawing");
                    list.add("Computer Programming Laboratory");
                    list.add("Constitution of India, Professional Ethics & Human");
                    list.add("Elements of Civil Engineering and Mechanics");
                    list.add("Elements of Mechanical Engineering");
                    list.add("Engineering Mathematics - 1");
                    list.add("Engineering Chemistry");
                    list.add("Engineering Chemistry Laboratory");
                    list.add("Engineering Mathematics - II");
                    list.add("Engineering Physics");
                    list.add("Engineering Physics Lab");
                    list.add("Environmental Studies");
                    list.add("Programming in C and Data Structures");
                    list.add("Workshop Practice Lab");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(AddDetails.this,
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    subjectSpinner.setAdapter(dataAdapter);
                }
                semesterSelected = String.valueOf(semesterSpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        branchesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branchSelected = String.valueOf(branchesSpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subjectSelected = String.valueOf(subjectSpinner.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        teacherTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = String.valueOf(teacherTypeSpinner.getSelectedItem());
                if (type.contentEquals("Class Teacher")) {
                    addStudent.setEnabled(true);
                    branchHeaderSubject.setEnabled(true);
                    subjectsHeaderSubject.setEnabled(false);
                    branchesSpinner.setEnabled(true);
                    subjectSpinner.setEnabled(false);
                    add.setEnabled(true);
                    teacherTypeSelected = "ClassTeacher";
                }
                if (type.contentEquals("Subject Teacher")) {
                    addStudent.setEnabled(false);
                    branchHeaderSubject.setEnabled(true);
                    subjectsHeaderSubject.setEnabled(true);
                    branchesSpinner.setEnabled(true);
                    subjectSpinner.setEnabled(true);
                    add.setEnabled(true);
                    teacherTypeSelected = "SubjectTeacher";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classNameString = className.getText().toString().toUpperCase();
                if (teacherTypeSelected.equals("SubjectTeacher")) {
                    mFirebaseDatabase = FirebaseDatabase
                            .getInstance()
                            .getReference()
                            .child("Users")
                            .child("Teacher")
                            .child(teacherTypeSelected)
                            .child(uid);
                    mFirebaseDatabase.child("TeacherOf" + number).child(branchSelected).child(semesterSelected).child(classNameString).child("Subject").setValue(subjectSelected);
                    number = number + 1;
                    mFirebaseDatabase.child("Number").setValue(number);
                }
                if (teacherTypeSelected.equals("ClassTeacher")) {
                    mFirebaseDatabase = FirebaseDatabase
                            .getInstance()
                            .getReference()
                            .child("Users")
                            .child("Teacher")
                            .child(teacherTypeSelected)
                            .child(uid);
                    mFirebaseDatabase.child("Branch").setValue(branchSelected);
                    mFirebaseDatabase.child("Semester").setValue(semesterSelected);
                    mFirebaseDatabase.child("ClassName").setValue(classNameString);
                    mFirebaseDatabase.child("Name").setValue(tUserName);
                    numbers = 1;
                    mFirebaseDatabase.child("Numbers").setValue(numbers);
                }
            }
        });
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDetails.this, AddStudentDetails.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed() {
        Intent myIntent = new Intent(AddDetails.this,
                TeacherControl.class);
        startActivity(myIntent);
    }
}
