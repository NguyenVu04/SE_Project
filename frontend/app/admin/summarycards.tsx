import React from 'react';

interface SummaryCard {
  title: string;
  value: number;
}
interface SummaryCardsProps {
  cards_prob: SummaryCard[];
}

const SummaryCards: React.FC<SummaryCardsProps> = ({cards_prob}) => {
  const cards = cards_prob;

  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 rounded-2xl">
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
