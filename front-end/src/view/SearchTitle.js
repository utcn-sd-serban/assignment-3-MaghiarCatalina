import React from "react";


const SearchTitle = ({foundQuestions, onBack}) => (
<div className="p-3 mb-2 bg-dark text-white">
    <h2>Questions found:</h2>
    <br/><br/>
    <table className="table table-dark table-striped table-bordered">
            <thead>
                <tr className="bg-success">
                    <th>Author</th>
                    <th>Title</th>
                    <th>Text</th>
                    <th>Date</th>
                    <th>Tags</th>
            
                </tr>
            </thead>
            <tbody>
                {
                    foundQuestions.map((question, index) => (
                        <tr key={index}>
                            <td>{question.author}</td>
                            <td>{question.title}</td>
                            <td>{question.text}</td>
                            <td>{question.date.toLocaleString()}</td>
                            <td>{question.tags}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <br/><br/>
        <button className="btn btn-warning" onClick={onBack}>Back</button>
</div>
);

export default SearchTitle;