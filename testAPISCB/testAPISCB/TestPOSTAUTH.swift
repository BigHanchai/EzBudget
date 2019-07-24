// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let testAPI = try? newJSONDecoder().decode(TestAPI.self, from: jsonData)

import Foundation

// MARK: - TestAPI
struct TestPOSTAUTH: Codable {
    let status: Status1
    let data: DataClass1
}

// MARK: - DataClass
struct DataClass1: Codable {
    let accessToken, tokenType: String
    let expiresIn, expiresAt: Int
    let refreshToken: String
    let refreshExpiresIn, refreshExpiresAt: Int
}

// MARK: - Status
struct Status1: Codable {
    let code: Int
    let statusDescription: String
    
    enum CodingKeys: String, CodingKey {
        case code
        case statusDescription = "description"
    }
}
