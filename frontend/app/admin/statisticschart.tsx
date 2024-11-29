import React from 'react';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  LineElement,
  PointElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js';

// Register the components
ChartJS.register(
  CategoryScale,
  LinearScale,
  LineElement,
  PointElement,
  Title,
  Tooltip,
  Legend
);

const StatisticsChart = () => {
  const data = {
    labels: ['10 Th. 8', '23 Th. 9', '10 Th. 9', '23 Th. 10'],
    datasets: [
      {
        label: 'Trang',
        data: [120, 150, 180, 200],
        borderColor: '#3b82f6',
        fill: false,
      },
      {
        label: 'Lệnh',
        data: [100, 130, 160, 190],
        borderColor: '#ffba1a',
        fill: false,
      },
      {
        label: 'Số dư (TB)',
        data: [50, 70, 110, 150],
        borderColor: '#3b8186',
        fill: false,
      },
    ],
  };
  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: 'left' as const,  // Position the legend to the left of the chart
        labels: {
          boxWidth: 20,      // Width of the color box in the legend
          padding: 15,       // Spacing between the color box and the label
        },
      },
    },
    scales: {
      x: {
        beginAtZero: true,
      },
      y: {
        beginAtZero: true,
      },
    },
  };
  return (
    <div className="bg-white p-4 rounded shadow">
      <h2 className="text-gray-600 mb-4">Thống kê</h2>
      <Line data={data} options={options} />
    </div>
  );
};

export default StatisticsChart;
