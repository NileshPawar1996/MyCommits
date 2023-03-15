import React, { useState, useEffect } from 'react';

const ProgressBar = () => {
    return (
        <tr>
            <td>
                <h4>Java</h4>
            </td>
            <td>

                <div class="progress">
                    <div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
                        <div class="progress-bar" style="width: 0%"></div>
                    </div>
                </div>

            </td>

        </tr>
    );
}
export default ProgressBar;