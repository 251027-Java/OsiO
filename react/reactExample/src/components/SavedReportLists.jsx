const SavedReportLists = ({ reports, onDelete }) => {
    if (reports.length === 0) {
        return null;
    }
    console.log("reports", reports);
    return (
        <div className='mt-8 bg-white p-6 rounded-2xl shadow-md border border-slate-200'>
            <h3 className='text-slate-500 font-bold border-b pb-2 mb-2 uppercase'>Saved Reports History</h3>
            <div className='grid gap-3'>{reports ? reports.map((report) => (
                <div key={report.id} className='flex justify-between items-center p-4 bg-slate-50 rounded-lg border border-slate-100 hover:border-indigo-200 transition-colors'>
                    <div className='flex flex-col'>
                        <p>Report {report.id.substring(2, 6)} </p>
                        <p>generated on {report.date} - {report.expenseCount} expenses</p>
                    </div>
                    <div className='flex items-center gap-4' >
                        <div className='text-indigo-700 font-mono font-bold text-lg'>
                            <p>${report.total}</p>
                        </div>
                        <div>
                            <button onClick= { () => (onDelete(report.id))} className='text-red-600 hover:text-red-800 font-bold text-lg rounded px-2 py-2'>
                                X
                            </button>
                        </div>
                    </div>
                </div>
            )) : ""}
            </div>
        </div>
    );
}
export default SavedReportLists;