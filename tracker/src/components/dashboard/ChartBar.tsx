import { BarChart } from "@mui/x-charts";

interface BarProps {
    income: number
    expenses: number
}

export default function Bar({income, expenses}: BarProps) {
    return (
        <BarChart
            series={[
                { data: [expenses], stack: 'A', label: 'Expenses'},
                { data: [income], stack: 'B', label: 'Income'},
            ]}
            yAxis={[{ scaleType: 'band', data: [''] }]}
            layout="horizontal"
            colors={['#f87171', '#4ade80']}
        />
    )
}