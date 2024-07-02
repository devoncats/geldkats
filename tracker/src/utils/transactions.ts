async function getTransactions(url: string) {
    const response = await fetch(url)
    return response.json()
}

function getTransactionsByMonth(transactions: Transaction[], month: string) {
    return transactions.filter(
        (transaction) => transaction.date.slice(5, 7) === month
    )
}

function getTotalBalance(totalIncomes: number, totalExpenses: number): number {
    return parseFloat(Math.abs(totalIncomes - totalExpenses).toFixed(2))
}

function getTotalExpenses(transactions: Transaction[]): number {
    return parseFloat(
        transactions
            .filter((transaction) => transaction.type === 'EXPENSE')
            .reduce((acc, transaction) => acc + transaction.amount, 0)
            .toFixed(2)
    )
}

function getTotalIncomes(transactions: Transaction[]): number {
    return parseFloat(
        transactions
            .filter((transaction) => transaction.type === 'INCOME')
            .reduce((acc, transaction) => acc + transaction.amount, 0)
            .toFixed(2)
    )
}

const month = new Date()
    .toLocaleString('default', { month: 'numeric' })
    .padStart(2, '0')

const transactions: Transaction[] = await getTransactions(
    'http://localhost:8080/api/transactions'
)

const transactionsByMonth = getTransactionsByMonth(transactions, month)

const totalExpenses = getTotalExpenses(transactions)

const totalIncomes = getTotalIncomes(transactions)

const totalBalance = getTotalBalance(totalIncomes, totalExpenses)

export {
    transactions,
    transactionsByMonth,
    totalBalance,
    totalExpenses,
    totalIncomes,
}
