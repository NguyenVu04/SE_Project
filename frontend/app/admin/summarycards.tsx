import React from 'react';

const SummaryCards = () => {
  const cards = [
    { title: 'Số máy in', value: 6 },
    { title: 'Lệnh in', value: 120 },
    { title: 'Số trang', value: 1560 },
  ];

  return (
    <div className="grid grid-cols-1 sm:grid-cols-3 gap-4 rounded-2xl">
      {cards.map((card, index) => (
        <div key={index} className="bg-white p-4 rounded shadow text-center">
          <h2 className="text-gray-600">{card.title}</h2>
          <p className="text-2xl font-bold">{card.value}</p>
        </div>
      ))}
    </div>
  );
};

export default SummaryCards;
