import React from 'react';

const TopBar = () => {
  return (
    <div className="bg-white shadow grid grid-cols-4 grid-rows-1  p-4 fixed top-0 left-64 w-[calc(100%-256px)] z-50 round-xl">
      {/* Left Section: Title */}
        <h1 className="text-xl font-semibold text-gray-800">Bảng điều khiển</h1>
        {/* Right Section: Add Button */}
        <button className=" ml-3 px-4 py-2 bg-hcmut-dark text-white rounded-md shadow hover:bg-hcmut-light focus:ring-2 focus:ring-blue-400">
            + Thêm sinh viên
        </button>
        <button className=" ml-3 px-4 py-2 bg-hcmut-dark text-white rounded-md shadow hover:bg-hcmut-light focus:ring-2 focus:ring-blue-400">
            + Thêm SPSO
        </button>
        <button className="ml-3 px-4 py-2 bg-hcmut-dark text-white rounded-md shadow hover:bg-hcmut-light focus:ring-2 focus:ring-blue-400">
            + Thêm máy in
        </button>    
    </div>
  );
};

export default TopBar;
