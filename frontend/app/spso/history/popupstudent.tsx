import React, { useState, useEffect } from "react";

interface StudentPopUpProps {
  value: string; // Passed from the parent
  toggleStudentForm: () => void;
  onSubmitStudentId: (studentId: string) => void; // Setter function to update parent state
}

export default function StudentPopUp({
  toggleStudentForm,
  onSubmitStudentId,
  value,
}: StudentPopUpProps) {
  const [studentId, setStudentId] = useState(value); // Initialize with the value passed from parent

  // Update local state if the parent value changes
  useEffect(() => {
    setStudentId(value);
  }, [value]);

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded-lg shadow-lg w-96">
        <h2 className="text-lg font-semibold mb-4">Chọn sinh viên</h2>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            onSubmitStudentId(studentId); // Update the parent with the new studentId
          }}
        >
          <div className="mb-4">
            <label className="block text-gray-700">Student ID</label>
            <input
              type="text"
              value={studentId}
              onChange={(e) => setStudentId(e.target.value)} // Manage the input field
              className="w-full border border-gray-300 p-2 rounded-md"
              placeholder="Nhập ID của sinh viên"
            />
          </div>
          <div className="flex justify-end">
            <button
              type="button"
              className="px-4 py-2 bg-gray-300 text-gray-700 rounded-md mr-2"
              onClick={toggleStudentForm}
            >
              Hủy
            </button>
            <button
              type="submit"
              className="px-4 py-2 hover:bg-red-600 bg-black text-white rounded-md"
              disabled={!studentId} // Disable the button if no studentId is entered
            >
              Chọn
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
