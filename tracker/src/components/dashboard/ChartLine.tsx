import { LineChart } from '@mui/x-charts/LineChart'

interface ChartProps {
    transactions: Transaction[];
}

export default function Chart ({ transactions }: ChartProps) {
    const data = transactions.map((transaction) => {
        if (transaction.type === 'EXPENSE') {
            return transaction.amount * -1
        }

        return transaction.amount
    })

    const cumulativeData = data.reduce((acc: number[], curr) => {
        const prevValue = acc.length > 0 ? acc[acc.length - 1] : 0;
        const currentValue = prevValue + curr;
        acc.push(currentValue)

        return acc;
    }, []);

    const length = cumulativeData.map((_, index) => index);

    return (
        <LineChart
            xAxis={[{ data: length }]}
            series={[
                {
                    data: cumulativeData
                }
            ]}
            grid={{ vertical: true, horizontal: true }}
        />
    );
}