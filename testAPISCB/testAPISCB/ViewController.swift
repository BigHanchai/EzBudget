//
//  ViewController.swift
//  testAPISCB
//
//  Created by Tnk. on 23/7/2562 BE.
//  Copyright © 2562 Tnk. All rights reserved.
//

import UIKit
import AlamofireImage
import Alamofire

class ViewController: UIViewController {
    
    var datasend : URL!
    var send2 = String()
    var send3 = String()
    var send4 = String()
    var datarespond = String()
    let appDelegate = UIApplication.shared.delegate as! AppDelegate
    override func viewDidLoad()
    {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.feedData()
    }
    
    func feedData()
    {
        let headers: HTTPHeaders = [
            "apikey":"l7067874eb3581470983e299486f380d96",
            "apisecret":"349751297a2f4a228116e5612f011b3a",
            "resourceOwnerId":"1",
            "requestUId":"762cfcaa-52ec-49a9-bb56-c293abb556c7",
            "response-channel":"mobile",
            "endState":"mobile_app",
            "accept-language":"EN"]
        let url = "https://api.partners.scb/partners/sandbox/v2/oauth/authorize"

        AF.request(url, method: .get,encoding: JSONEncoding.default, headers: headers).responseJSON{
            (response) in

            switch response.result
            {
            case .success:
                do
                {
                    let result = try JSONDecoder().decode(TestAPI.self, from: response.data!)
                    self.datasend = result.data.callbackURL.toURL()
                    UIApplication.shared.open(self.datasend as URL)

                }
                catch let err
                {
                    print(err)
                }
            case .failure(let error):
                print("network error \(error.localizedDescription)")
            }
        }
    }
    
    func testAPI(AUTHCODE:String)
    {
        print("Auth:\(AUTHCODE)")
        let body = [
            "applicationKey" : "l7067874eb3581470983e299486f380d96",
            "applicationSecret" : "349751297a2f4a228116e5612f011b3a",
            "authCode":AUTHCODE
        ]
        
        let header:HTTPHeaders = [
            "Content-Type": "application/json",
            "resourceOwnerId": "1",
            "requestUId": "762cfcaa-52ec-49a9-bb56-c293abb556c7",
            "accept-language": "EN"
        ]
        
        AF.request("https://api.partners.scb/partners/sandbox/v1/oauth/token", method: .post, parameters: body, encoding: JSONEncoding.default, headers: header).responseJSON { (response) in
            
            switch response.result{
            case .success:
                
                do {
                    
                    let result = try JSONDecoder().decode(TestPOSTAUTH.self, from: response.data!)
                    let data = String(describing: response.response?.allHeaderFields["resourceownerid"])
                    let resourceOwnerId = String()
                    self.send2 = result.data.accessToken
                    self.send3 = String(describing: response.response?.allHeaderFields["resourceownerid"])
                    self.changepage()
                }catch{
                    
                }
            case .failure(let error):
                print("network error: \(error.localizedDescription)")
            }
        }
    }
    func changepage()
    {
        performSegue(withIdentifier: "testapi", sender: self)
    }
    override func prepare(for segue: UIStoryboardSegue, sender: Any?)
    {
        if segue.identifier == "testapi"
        {
            let profileviewController = segue.destination as! ProfileViewController
            profileviewController.accesstoken = self.send2
            profileviewController.resourceid = self.send3
        }
    }
}

extension String
{
    func toURL() -> URL {
        return URL(string: self)!
    }
}




