import React from 'react';

interface Student {
  id: string;
  email: string;
  firstName: string;
  lastName: string;
  dateOfBirth: string;
  balance: number;
}

interface StudentListProps {
  students_prob: Student[];
}

const StudentList: React.FC<StudentListProps> = ({students_prob}) => {
  const students = students_prob;

  return (
    <div className="bg-white p-4 rounded shadow">
      <h2 className="text-gray-600 mb-4">Danh sinh viên</h2>
      <ul>
        {students.map((student, index) => (
          <li key={index} className="mb-2">
            <div className="flex justify-between">
              <span>{student.firstName}-{student.lastName}</span>
              <span className="text-sm text-gray-500">số dư: {student.balance}</span>
            </div>
            <span className="text-sm text-gray-500">{student.email}</span>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default StudentList;
