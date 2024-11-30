import React, { useState, useEffect } from "react";

interface PrinterPopUpProps {
  value: string; // Passed from the parent
  togglePrinterForm: () => void;
  onSubmitPrinterId: (printerId: string) => void; // Setter function to update parent state
}

export default function PrinterPopUp({
    value,
    togglePrinterForm,
    onSubmitPrinterId,
}: PrinterPopUpProps) {
  const [printerId, setPrinterId] = useState(value); // Initialize with the value passed from parent

  // Update local state if the parent value changes
  useEffect(() => {
    setPrinterId(value);
  }, [value]);

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded-lg shadow-lg w-96">
        <h2 className="text-lg font-semibold mb-4">Chọn máy in</h2>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            onSubmitPrinterId(printerId); // Update the parent with the new printerId
          }}
        >
          <div className="mb-4">
            <label className="block text-gray-700">Printer ID</label>
            <input
              type="text"
              value={printerId}
              onChange={(e) => setPrinterId(e.target.value)} // Manage the input field
              className="w-full border border-gray-300 p-2 rounded-md"
              placeholder="Nhập ID của sinh viên"
            />
          </div>
          <div className="flex justify-end">
            <button
              type="button"
              className="px-4 py-2 bg-gray-300 text-gray-700 rounded-md mr-2"
              onClick={togglePrinterForm}
            >
              Hủy
            </button>
            <button
              type="submit"
              className="px-4 py-2 hover:bg-red-600 bg-black text-white rounded-md"
              disabled={!printerId} // Disable the button if no printerId is entered
            >
              Chọn
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
