import SavedReportLists from "../SavedReportLists";

const SavedReportsPage = ({ savedReports, deleteReportHandler } ) => {
    return(
        <div>
            <h1>Saved Reports</h1>
            <SavedReportLists reports={savedReports} onDelete={deleteReportHandler} />
        </div>
    );
}

export default SavedReportsPage;