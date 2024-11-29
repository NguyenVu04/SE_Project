import React from 'react';

const TopBar = () => {
  return (
    <div className="bg-white shadow flex items-center p-4 fixed top-0 left-64 w-[calc(100%-256px)] z-50 round-xl">
      {/* Left Section: Title */}
      <h1 className="text-xl font-semibold text-gray-800 ">Bảng điều khiển</h1>
      {/* Center Section: Search Bar */}
      <div className="flex-1 mx-2">
        <input
          type="text"
          placeholder="Tìm kiếm"
          className="w-3/4 px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-hcmut-dark focus:border-hcmut-dark"
        />
        {/* Right Section: Add Button */}
        <button className="ml-3 px-4 py-2 bg-hcmut-dark text-white rounded-md shadow hover:bg-hcmut-light focus:ring-2 focus:ring-blue-400">
            + Thêm máy in
        </button>
      </div>

      
    </div>
  );
};

export default TopBar;
